package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.*;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.annotation.CheckClubAuth;
import edu.fzu.zhishe.core.annotation.IsClubMember;
import edu.fzu.zhishe.core.annotation.IsLogin;
import edu.fzu.zhishe.core.constant.ClubStatueEnum;
import edu.fzu.zhishe.core.dao.*;
import edu.fzu.zhishe.core.dto.*;
import edu.fzu.zhishe.core.param.CmsClubInfoParam;
import edu.fzu.zhishe.core.param.CmsClubMemberQuery;
import edu.fzu.zhishe.core.param.OrderByParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.service.CmsClubService;
import edu.fzu.zhishe.core.service.SysUserService;
import java.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import edu.fzu.zhishe.core.error.PostErrorEnum;
import edu.fzu.zhishe.core.error.ClubErrorEnum;

/**
 * 社团管理服务层
 * @author wjh674
 */
@Service
public class CmsClubServiceImpl implements CmsClubService {

    @Autowired
    CmsClubCreateApplyMapper clubCreateApplyMapper;


    @Autowired
    CmsClubDisbandApplyMapper clubDisbandApplyMapper;


    @Autowired
    CmsClubJoinApplyMapper clubJoinApplyMapper;


    @Autowired
    CmsClubMapper clubMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    CmsUserClubRelMapper userClubRelMapper;

    @Autowired
    CmsQuitNoticeMapper quitNoticeMapper;

    @Autowired
    CmsChiefChangeApplyMapper chiefChangeApplyMapper;

    @Autowired
    CmsOfficialChangeApplyMapper officialChangeApplyMapper;

    @Autowired
    CmsClubCertificationDAO cmsClubCertificationDAO;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    private CmsClubDAO clubDAO;

    @Autowired
    CmsMemberHonorMapper honorMapper;

    @Autowired
    SysRoleMapper roleMapper;

    @Autowired
    CmsClubPictureMapper pictureMapper;

    @Override
    public boolean isClubMember(Integer clubId) {
        ClubStatueEnum clubStatue = getClubStatue(clubId);
        return clubStatue == ClubStatueEnum.CHIEF || clubStatue == ClubStatueEnum.MEMBER;
    }

    @Override
    public ClubStatueEnum getClubStatue(Integer clubId) {
        // 找不到社团
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        if (club == null) {
            return ClubStatueEnum.NONE;
        }

        SysUser currentUser = sysUserService.getCurrentUser();
        Integer userId = currentUser.getId();
        if (club.getChiefId().equals(userId)) {
            return ClubStatueEnum.CHIEF;
        }

        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria()
            .andClubIdEqualTo(clubId)
            .andUserIdEqualTo(userId);
        List<CmsUserClubRel> userClubRels = userClubRelMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userClubRels)) {
            return ClubStatueEnum.NONE;
        }

        return ClubStatueEnum.MEMBER;
    }

    /**获取推荐社团列表*/
    @Override
    public List<CmsClubBriefDTO> listHotClub(PaginationParam paginationParam, OrderByParam orderByParam) {
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        List<CmsClubBriefDTO> list = clubDAO.listHotClub(orderByParam);
        for (CmsClubBriefDTO club : list) {
            if (!isClubMember(club.getId())) {
                club.setJoinState("已加入");
            }
            else {
                club.setJoinState("未加入");
            }
        }
        return list;
    }

    /**获取所有社团列表*/
    @Override
    public List<CmsClubBriefDTO> listClub(
        PaginationParam paginationParam,
        OrderByParam orderByParam,
        String keyword,
        String type,
        Integer state) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        List<CmsClubBriefDTO> list = clubDAO.listClub(keyword,type,state);
        for (CmsClubBriefDTO club : list) {
            if (!isClubMember(club.getId())) {
                club.setJoinState("已加入");
            }
            else {
                club.setJoinState("未加入");
            }
        }
        return list;
    }

    /**根据社团ID查找社团*/
    @Override
    public CmsClubDetailDTO getClubById(Integer id) {
        CmsClub club = clubMapper.selectByPrimaryKey(id);
        SysUser user = sysUserMapper.selectByPrimaryKey(club.getChiefId());
        CmsClubDetailDTO data = new CmsClubDetailDTO();
        if (club.getId() != null) {
            data.setId(club.getId());
            data.setName(club.getName());
            data.setChiefName(user.getNickname());
            data.setAvatarUrl(club.getAvatarUrl());
            data.setSlogan(club.getSlogan());
            data.setType(club.getType());
            data.setMemberCount(club.getMemberCount());
            data.setQqGroup(club.getQqGroup());
        }
        return data;
    }

    /**获取加入的社团列表（社团内身份为社员）*/
    @Override
    public List<CmsClubBriefDTO> listJoinedClub(
        PaginationParam paginationParam,
        OrderByParam orderByParam,
        Integer userId) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return clubDAO.listJoinedClub(orderByParam, userId);
    }

    /**获取管理的社团列表（社团内身份为社长）*/
    @Override
    public List<CmsClubBriefDTO> listManagedClub(
        PaginationParam paginationParam,
        OrderByParam orderByParam,
        Integer userId) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return clubDAO.listManagedClub(orderByParam, userId);
    }

    /**获取加入社团申请列表（学生查看自己发出的加入社团申请）*/
    @IsLogin
    @Override
    public List<CmsClubJoinApplyDTO> listJoinClubApply(
        PaginationParam paginationParam,
        OrderByParam orderByParam) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        orderByParam.setOrder("desc");
        return clubDAO.listJoinClubApply(orderByParam, sysUserService.getCurrentUser().getId());
    }

    /**获取创建社团申请列表（学生查看自己发出的创建社团申请）*/
    @IsLogin
    @Override
    public List<CmsClubCreateApplyDTO> listCreateClubApply(
        PaginationParam paginationParam,
        OrderByParam orderByParam) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        orderByParam.setOrder("desc");
        return clubDAO.listCreateClubApply(orderByParam, sysUserService.getCurrentUser().getId());
    }

    /**查看社员列表*/
    @Override
    @IsClubMember
    public List<CmsClubMemberBriefDTO> listClubMember(
        PaginationParam paginationParam,
        Integer clubId,
        CmsClubMemberQuery clubMemberQuery) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return clubDAO.listClubMember(clubId, clubMemberQuery);
    }

    /**查看社员详细信息*/
    @Override
    @IsClubMember
    public CmsClubMemberDetailDTO getClubMemberInfo(Integer clubId, Integer userId) {

        CmsUserClubRelExample userClubRel = new CmsUserClubRelExample();
        userClubRel.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(userId);
        List<CmsUserClubRel> userClubList = userClubRelMapper.selectByExample(userClubRel);

        SysUser user = sysUserMapper.selectByPrimaryKey(userId);
        CmsClubMemberDetailDTO data = new CmsClubMemberDetailDTO();
        //将用户字段和data字段相同的，复制到data里面
        BeanUtils.copyProperties(user, data);
        CmsUserClubRel userClub = userClubList.get(0);
        CmsMemberHonor honor = honorMapper.selectByPrimaryKey(userClub.getHonorId());
        data.setHonor(honor.getName());
        SysRole role = roleMapper.selectByPrimaryKey(userClub.getRoleId());
        data.setRole(role.getDescription());
        data.setCredit(userClub.getCredit());
        return data;
    }

    /**添加社员*/
    @Override
    @Transactional(rollbackFor = NullPointerException.class)
    public Integer saveClubMember(Integer clubId, Integer userId) {
        CmsUserClubRel clubRel = new CmsUserClubRel();
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        if (club == null) {
            Asserts.fail(PostErrorEnum.CLUB_NOT_EXIST);
        }
        if (club.getDeleteStatus() == 1) {
            Asserts.fail(PostErrorEnum.CLUB_ALREADY_DISBAND);
        }
        clubRel.setClubId(clubId);
        clubRel.setUserId(userId);
        clubRel.setCredit(0);
        clubRel.setHonorId(1);
        clubRel.setRoleId(2);
        clubRel.setJoinDate(new Date());
        userClubRelMapper.insert(clubRel);
        club.setMemberCount(club.getMemberCount()+1);
        return clubMapper.updateByPrimaryKey(club);
    }

    /**删除社员*/
    @Override
    @CheckClubAuth("3")
    @Transactional(rollbackFor = NullPointerException.class)
    public Integer deleteClubMember(Integer clubId, Integer userId) {
        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(userId);
        List<CmsUserClubRel> rel = userClubRelMapper.selectByExample(example);
        if (CollUtil.isEmpty(rel)) {
            Asserts.fail(ClubErrorEnum.USER_NOT_IN);
        }
        userClubRelMapper.deleteByExample(example);
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        club.setMemberCount(club.getMemberCount() - 1);
        return clubMapper.updateByPrimaryKey(club);
    }

    /**修改社团信息接口*/
    @Override
    @CheckClubAuth("3")
    public Integer updateClubInfo(Integer clubId, CmsClubInfoParam clubInfoParam) {

        if (StrUtil.isEmpty(clubInfoParam.getType())) {
            Asserts.fail(ClubErrorEnum.EMPTY_TYPE);
        }
        CmsClub club = new CmsClub();
        club.setId(clubId);
        BeanUtils.copyProperties(clubInfoParam, club);
        return clubMapper.updateByPrimaryKeySelective(club);
    }

    /**修改社团头像*/
    @Override
    @CheckClubAuth("3")
    public Integer updateClubAvatarUrl(Integer clubId, String avatarUrl) {

        if (StrUtil.isEmpty(avatarUrl)) {
            Asserts.fail(ClubErrorEnum.EMPTY_FILLED);
        }
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        club.setAvatarUrl(avatarUrl);
        return clubMapper.updateByPrimaryKeySelective(club);
    }

    /**获取社团走马灯图片*/
    @Override
    public String[] getClubPicture(Integer clubId) {
        CmsClubPictureExample pictureExample = new CmsClubPictureExample();
        pictureExample.createCriteria().andClubIdEqualTo(clubId);
        List<CmsClubPicture> pictureList = pictureMapper.selectByExample(pictureExample);
        if (CollUtil.isEmpty(pictureList)) {
            Asserts.fail(PostErrorEnum.CLUB_NOT_EXIST);
        }
        CmsClubPicture picture = pictureList.get(0);
        String[] urls = {null,null,null,null,null};
        if (picture.getClubId() != null) {
            urls[0] = picture.getPic1Url();
            urls[1] = picture.getPic2Url();
            urls[2] = picture.getPic3Url();
            urls[3] = picture.getPic4Url();
            urls[4] = picture.getPic5Url();
        }
        return urls;
    }

    /**修改社团走马灯图片*/
    @Override
    @CheckClubAuth("3")
    public Integer updateClubPictureUrl(Integer clubId, String[] pictureUrls) {
        CmsClubPictureExample pictureExample = new CmsClubPictureExample();
        pictureExample.createCriteria().andClubIdEqualTo(clubId);
        List<CmsClubPicture> pictureList = pictureMapper.selectByExample(pictureExample);
        CmsClubPicture picture = pictureList.get(0);
        for (int i = 0; i < pictureUrls.length; i ++ ) {
            if (pictureUrls[i] == null || pictureUrls[i].length() == 0) {
                continue;
            }
            switch (i) {
                case 0:
                    picture.setPic1Url(pictureUrls[i]);
                    break;
                case 1:
                    picture.setPic2Url(pictureUrls[i]);
                    break;
                case 2:
                    picture.setPic3Url(pictureUrls[i]);
                    break;
                case 3:
                    picture.setPic4Url(pictureUrls[i]);
                    break;
                case 4:
                    picture.setPic5Url(pictureUrls[i]);
                    break;
                default:
                    Asserts.fail(ClubErrorEnum.WRONG_FILLED);
            }
        }
        return pictureMapper.updateByPrimaryKeySelective(picture);
    }

}
