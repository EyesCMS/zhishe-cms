package edu.fzu.zhishe.core.util;

import edu.fzu.zhishe.cms.model.CmsActivity;
import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.FmsPost;
import edu.fzu.zhishe.core.constant.ActivityStateEnum;
import edu.fzu.zhishe.core.constant.DeleteStateEnum;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/27/2020
 */
public class NotExistUtil {

    /**
     * check existence of club
     * @param club
     * @return true if club is not exist
     */
    public static boolean check(CmsClub club) {
        return club == null || club.getDeleteStatus() == DeleteStateEnum.Deleted.getValue();
    }

    /**
     * check existence of activity
     * @param activity
     * @return true if activity is not exist
     */
    public static boolean check(CmsActivity activity) {
        return activity == null || activity.getState().equals(ActivityStateEnum.DELETED.getValue());
    }

    /**
     * check existence of post
     * @param post
     * @return true if post is not exist
     */
    public static boolean check(FmsPost post) {
        return post == null || post.getDeleteState().equals(ActivityStateEnum.DELETED.getValue());
    }
}
