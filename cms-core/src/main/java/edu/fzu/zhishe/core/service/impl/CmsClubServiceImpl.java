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



    @Override
    public List<CmsClubBriefDTO> listHotClub(PaginationParam paginationParam, OrderByParam orderByParam) {
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return clubDAO.listHotClub(orderByParam);
    }

    @Override
    public List<CmsClubBriefDTO> listClub(PaginationParam paginationParam,
        OrderByParam orderByParam, String keyword, String type, Integer state) {
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return clubDAO.listClub(keyword,type,state);
    }

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

    @Override
    public List<CmsClubBriefDTO> listJoinedClub(PaginationParam paginationParam, OrderByParam orderByParam, Integer userId) {
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return clubDAO.listJoinedClub(orderByParam, userId);
    }

    @Override
    public List<CmsClubBriefDTO> listManagedClub(PaginationParam paginationParam, OrderByParam orderByParam, Integer userId) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return clubDAO.listManagedClub(orderByParam, userId);
    }

    @Override
    public List<CmsClubJoinApplyDTO> listJoinClubApply(
            PaginationParam paginationParam, OrderByParam orderByParam) {
        SysUser currentUser = sysUserService.getCurrentUser();
        if (currentUser == null) {
            Asserts.unAuthorized();
        }

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        orderByParam.setOrder("desc");
        return clubDAO.listJoinClubApply(orderByParam, currentUser.getId());
    }

    @Override
    public List<CmsClubCreateApplyDTO> listCreateClubApply(
            PaginationParam paginationParam, OrderByParam orderByParam) {
        SysUser currentUser = sysUserService.getCurrentUser();
        if (currentUser == null) {
            Asserts.unAuthorized();
        }

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        orderByParam.setOrder("desc");
        return clubDAO.listCreateClubApply(orderByParam, currentUser.getId());
    }

    @Override
    @IsClubMember
    public List<CmsClubMemberBriefDTO> listClubMember(PaginationParam paginationParam, Integer clubId,
            CmsClubMemberQuery clubMemberQuery) {
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return clubDAO.listClubMember(clubId, clubMemberQuery);
    }

    @Override
    @IsClubMember
    public CmsClubMemberDetailDTO showClubMemberInfo(Integer clubId, Integer userId) {

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

    @Override
    @Transactional
    public Integer addClubMember(Integer clubId, Integer userId){
        CmsUserClubRel clubRel = new CmsUserClubRel();
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
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

    @Override
    @Transactional
    public Integer deleteClubMember(Integer clubId, Integer userId) {

        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(userId);
        userClubRelMapper.deleteByExample(example);
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        club.setMemberCount(club.getMemberCount() - 1);
        return clubMapper.updateByPrimaryKey(club);
    }

    //修改社团信息接口
    @Override
    @CheckClubAuth("3")
    public Integer updateClubInfo(Integer clubId, CmsClubInfoParam clubInfoParam) {

        if (StrUtil.isEmpty(clubInfoParam.getType())) {
            Asserts.fail("社团类型不能为空！");
        }
        CmsClub club = new CmsClub();
        club.setId(clubId);
        BeanUtils.copyProperties(clubInfoParam, club);
        return clubMapper.updateByPrimaryKeySelective(club);
    }

    @Override
    @CheckClubAuth("3")
    public Integer alterClubAvatarUrl(Integer clubId, String avatarUrl){

        if (StrUtil.isEmpty(avatarUrl)) {
            Asserts.fail("输入不能为空！");
        }
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        club.setAvatarUrl(avatarUrl);
        return clubMapper.updateByPrimaryKeySelective(club);
    }

    @Override
    public CmsClubPictureDTO getClubPicture(Integer clubId) {
        CmsClubPictureExample pictureExample = new CmsClubPictureExample();
        pictureExample.createCriteria().andClubIdEqualTo(clubId);
        List<CmsClubPicture> pictureList = pictureMapper.selectByExample(pictureExample);
        if (CollUtil.isEmpty(pictureList)) {
            Asserts.fail("club is not existed");
        }
        CmsClubPicture picture = pictureList.get(0);
        CmsClubPictureDTO data = new CmsClubPictureDTO();
        if (picture.getClubId() != null) {
            data.setUrl1(picture.getPic1Url());
            data.setUrl2(picture.getPic2Url());
            data.setUrl3(picture.getPic3Url());
            data.setUrl4(picture.getPic4Url());
            data.setUrl5(picture.getPic5Url());
        }
        return data;
    }

    @Override
    @CheckClubAuth("3")
    public Integer alterClubPictureUrl(Integer clubId, String pictureUrl, Integer index) {

        if (StrUtil.isEmpty(pictureUrl)) {
            Asserts.fail("输入不能为空！");
        }
        CmsClubPictureExample pictureExample = new CmsClubPictureExample();
        pictureExample.createCriteria().andClubIdEqualTo(clubId);
        List<CmsClubPicture> pictureList = pictureMapper.selectByExample(pictureExample);
        if (pictureList.size() == 0) {
            Asserts.fail("club is not existed");
        }
        CmsClubPicture picture = pictureList.get(0);
        switch (index) {
            case 1:
                picture.setPic1Url(pictureUrl);
                break;
            case 2:
                picture.setPic2Url(pictureUrl);
                break;
            case 3:
                picture.setPic3Url(pictureUrl);
                break;
            case 4:
                picture.setPic4Url(pictureUrl);
                break;
            case 5:
                picture.setPic5Url(pictureUrl);
                break;
            default:
                Asserts.fail("输入参数有误！");
        }

        return pictureMapper.updateByPrimaryKeySelective(picture);
    }
}
