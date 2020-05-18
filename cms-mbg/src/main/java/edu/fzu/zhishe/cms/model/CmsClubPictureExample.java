package edu.fzu.zhishe.cms.model;

import java.util.ArrayList;
import java.util.List;

public class CmsClubPictureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CmsClubPictureExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andClubIdIsNull() {
            addCriterion("club_id is null");
            return (Criteria) this;
        }

        public Criteria andClubIdIsNotNull() {
            addCriterion("club_id is not null");
            return (Criteria) this;
        }

        public Criteria andClubIdEqualTo(Integer value) {
            addCriterion("club_id =", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotEqualTo(Integer value) {
            addCriterion("club_id <>", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdGreaterThan(Integer value) {
            addCriterion("club_id >", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("club_id >=", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdLessThan(Integer value) {
            addCriterion("club_id <", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdLessThanOrEqualTo(Integer value) {
            addCriterion("club_id <=", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdIn(List<Integer> values) {
            addCriterion("club_id in", values, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotIn(List<Integer> values) {
            addCriterion("club_id not in", values, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdBetween(Integer value1, Integer value2) {
            addCriterion("club_id between", value1, value2, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotBetween(Integer value1, Integer value2) {
            addCriterion("club_id not between", value1, value2, "clubId");
            return (Criteria) this;
        }

        public Criteria andPic1UrlIsNull() {
            addCriterion("pic1_url is null");
            return (Criteria) this;
        }

        public Criteria andPic1UrlIsNotNull() {
            addCriterion("pic1_url is not null");
            return (Criteria) this;
        }

        public Criteria andPic1UrlEqualTo(String value) {
            addCriterion("pic1_url =", value, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic1UrlNotEqualTo(String value) {
            addCriterion("pic1_url <>", value, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic1UrlGreaterThan(String value) {
            addCriterion("pic1_url >", value, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic1UrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic1_url >=", value, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic1UrlLessThan(String value) {
            addCriterion("pic1_url <", value, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic1UrlLessThanOrEqualTo(String value) {
            addCriterion("pic1_url <=", value, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic1UrlLike(String value) {
            addCriterion("pic1_url like", value, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic1UrlNotLike(String value) {
            addCriterion("pic1_url not like", value, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic1UrlIn(List<String> values) {
            addCriterion("pic1_url in", values, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic1UrlNotIn(List<String> values) {
            addCriterion("pic1_url not in", values, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic1UrlBetween(String value1, String value2) {
            addCriterion("pic1_url between", value1, value2, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic1UrlNotBetween(String value1, String value2) {
            addCriterion("pic1_url not between", value1, value2, "pic1Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlIsNull() {
            addCriterion("pic2_url is null");
            return (Criteria) this;
        }

        public Criteria andPic2UrlIsNotNull() {
            addCriterion("pic2_url is not null");
            return (Criteria) this;
        }

        public Criteria andPic2UrlEqualTo(String value) {
            addCriterion("pic2_url =", value, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlNotEqualTo(String value) {
            addCriterion("pic2_url <>", value, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlGreaterThan(String value) {
            addCriterion("pic2_url >", value, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic2_url >=", value, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlLessThan(String value) {
            addCriterion("pic2_url <", value, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlLessThanOrEqualTo(String value) {
            addCriterion("pic2_url <=", value, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlLike(String value) {
            addCriterion("pic2_url like", value, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlNotLike(String value) {
            addCriterion("pic2_url not like", value, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlIn(List<String> values) {
            addCriterion("pic2_url in", values, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlNotIn(List<String> values) {
            addCriterion("pic2_url not in", values, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlBetween(String value1, String value2) {
            addCriterion("pic2_url between", value1, value2, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic2UrlNotBetween(String value1, String value2) {
            addCriterion("pic2_url not between", value1, value2, "pic2Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlIsNull() {
            addCriterion("pic3_url is null");
            return (Criteria) this;
        }

        public Criteria andPic3UrlIsNotNull() {
            addCriterion("pic3_url is not null");
            return (Criteria) this;
        }

        public Criteria andPic3UrlEqualTo(String value) {
            addCriterion("pic3_url =", value, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlNotEqualTo(String value) {
            addCriterion("pic3_url <>", value, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlGreaterThan(String value) {
            addCriterion("pic3_url >", value, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic3_url >=", value, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlLessThan(String value) {
            addCriterion("pic3_url <", value, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlLessThanOrEqualTo(String value) {
            addCriterion("pic3_url <=", value, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlLike(String value) {
            addCriterion("pic3_url like", value, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlNotLike(String value) {
            addCriterion("pic3_url not like", value, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlIn(List<String> values) {
            addCriterion("pic3_url in", values, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlNotIn(List<String> values) {
            addCriterion("pic3_url not in", values, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlBetween(String value1, String value2) {
            addCriterion("pic3_url between", value1, value2, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic3UrlNotBetween(String value1, String value2) {
            addCriterion("pic3_url not between", value1, value2, "pic3Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlIsNull() {
            addCriterion("pic4_url is null");
            return (Criteria) this;
        }

        public Criteria andPic4UrlIsNotNull() {
            addCriterion("pic4_url is not null");
            return (Criteria) this;
        }

        public Criteria andPic4UrlEqualTo(String value) {
            addCriterion("pic4_url =", value, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlNotEqualTo(String value) {
            addCriterion("pic4_url <>", value, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlGreaterThan(String value) {
            addCriterion("pic4_url >", value, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic4_url >=", value, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlLessThan(String value) {
            addCriterion("pic4_url <", value, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlLessThanOrEqualTo(String value) {
            addCriterion("pic4_url <=", value, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlLike(String value) {
            addCriterion("pic4_url like", value, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlNotLike(String value) {
            addCriterion("pic4_url not like", value, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlIn(List<String> values) {
            addCriterion("pic4_url in", values, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlNotIn(List<String> values) {
            addCriterion("pic4_url not in", values, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlBetween(String value1, String value2) {
            addCriterion("pic4_url between", value1, value2, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic4UrlNotBetween(String value1, String value2) {
            addCriterion("pic4_url not between", value1, value2, "pic4Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlIsNull() {
            addCriterion("pic5_url is null");
            return (Criteria) this;
        }

        public Criteria andPic5UrlIsNotNull() {
            addCriterion("pic5_url is not null");
            return (Criteria) this;
        }

        public Criteria andPic5UrlEqualTo(String value) {
            addCriterion("pic5_url =", value, "pic5Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlNotEqualTo(String value) {
            addCriterion("pic5_url <>", value, "pic5Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlGreaterThan(String value) {
            addCriterion("pic5_url >", value, "pic5Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic5_url >=", value, "pic5Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlLessThan(String value) {
            addCriterion("pic5_url <", value, "pic5Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlLessThanOrEqualTo(String value) {
            addCriterion("pic5_url <=", value, "pic5Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlLike(String value) {
            addCriterion("pic5_url like", value, "pic5Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlNotLike(String value) {
            addCriterion("pic5_url not like", value, "pic5Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlIn(List<String> values) {
            addCriterion("pic5_url in", values, "pic5Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlNotIn(List<String> values) {
            addCriterion("pic5_url not in", values, "pic5Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlBetween(String value1, String value2) {
            addCriterion("pic5_url between", value1, value2, "pic5Url");
            return (Criteria) this;
        }

        public Criteria andPic5UrlNotBetween(String value1, String value2) {
            addCriterion("pic5_url not between", value1, value2, "pic5Url");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}