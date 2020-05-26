package edu.fzu.zhishe.core.util;

import edu.fzu.zhishe.common.exception.Asserts;
import java.util.List;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/26/2020
 */
public class CreditUtil {

    /**
     * 根据各个等级的下界计算某个积分处于哪个等级
     * @param lowerBounds 等级下界列表
     * @param credit 积分数
     * @return 积分对应的等级
     */
    public static int getGradeByCredit(List<Integer> lowerBounds, Integer credit) {
        int grade = 0;
        for (Integer lowerBound : lowerBounds) {
            if (credit >= lowerBound) {
                grade++;
            } else {
                break;
            }
        }
        if (grade == 0) {
            Asserts.fail("无法计算活跃度，积分不在任何一个规定活跃度的区间");
        }
        return grade;
    }
}
