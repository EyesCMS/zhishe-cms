package edu.fzu.zhishe.cms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CmsActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CmsActivityExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andBodyIsNull() {
            addCriterion("body is null");
            return (Criteria) this;
        }

        public Criteria andBodyIsNotNull() {
            addCriterion("body is not null");
            return (Criteria) this;
        }

        public Criteria andBodyEqualTo(String value) {
            addCriterion("body =", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotEqualTo(String value) {
            addCriterion("body <>", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyGreaterThan(String value) {
            addCriterion("body >", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyGreaterThanOrEqualTo(String value) {
            addCriterion("body >=", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLessThan(String value) {
            addCriterion("body <", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLessThanOrEqualTo(String value) {
            addCriterion("body <=", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLike(String value) {
            addCriterion("body like", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotLike(String value) {
            addCriterion("body not like", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyIn(List<String> values) {
            addCriterion("body in", values, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotIn(List<String> values) {
            addCriterion("body not in", values, "body");
            return (Criteria) this;
        }

        public Criteria andBodyBetween(String value1, String value2) {
            addCriterion("body between", value1, value2, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotBetween(String value1, String value2) {
            addCriterion("body not between", value1, value2, "body");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("img_url not between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andStarDateIsNull() {
            addCriterion("star_date is null");
            return (Criteria) this;
        }

        public Criteria andStarDateIsNotNull() {
            addCriterion("star_date is not null");
            return (Criteria) this;
        }

        public Criteria andStarDateEqualTo(Date value) {
            addCriterion("star_date =", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateNotEqualTo(Date value) {
            addCriterion("star_date <>", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateGreaterThan(Date value) {
            addCriterion("star_date >", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateGreaterThanOrEqualTo(Date value) {
            addCriterion("star_date >=", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateLessThan(Date value) {
            addCriterion("star_date <", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateLessThanOrEqualTo(Date value) {
            addCriterion("star_date <=", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateIn(List<Date> values) {
            addCriterion("star_date in", values, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateNotIn(List<Date> values) {
            addCriterion("star_date not in", values, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateBetween(Date value1, Date value2) {
            addCriterion("star_date between", value1, value2, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateNotBetween(Date value1, Date value2) {
            addCriterion("star_date not between", value1, value2, "starDate");
            return (Criteria) this;
        }

        public Criteria andEndDataIsNull() {
            addCriterion("end_data is null");
            return (Criteria) this;
        }

        public Criteria andEndDataIsNotNull() {
            addCriterion("end_data is not null");
            return (Criteria) this;
        }

        public Criteria andEndDataEqualTo(Date value) {
            addCriterion("end_data =", value, "endData");
            return (Criteria) this;
        }

        public Criteria andEndDataNotEqualTo(Date value) {
            addCriterion("end_data <>", value, "endData");
            return (Criteria) this;
        }

        public Criteria andEndDataGreaterThan(Date value) {
            addCriterion("end_data >", value, "endData");
            return (Criteria) this;
        }

        public Criteria andEndDataGreaterThanOrEqualTo(Date value) {
            addCriterion("end_data >=", value, "endData");
            return (Criteria) this;
        }

        public Criteria andEndDataLessThan(Date value) {
            addCriterion("end_data <", value, "endData");
            return (Criteria) this;
        }

        public Criteria andEndDataLessThanOrEqualTo(Date value) {
            addCriterion("end_data <=", value, "endData");
            return (Criteria) this;
        }

        public Criteria andEndDataIn(List<Date> values) {
            addCriterion("end_data in", values, "endData");
            return (Criteria) this;
        }

        public Criteria andEndDataNotIn(List<Date> values) {
            addCriterion("end_data not in", values, "endData");
            return (Criteria) this;
        }

        public Criteria andEndDataBetween(Date value1, Date value2) {
            addCriterion("end_data between", value1, value2, "endData");
            return (Criteria) this;
        }

        public Criteria andEndDataNotBetween(Date value1, Date value2) {
            addCriterion("end_data not between", value1, value2, "endData");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andMemberCountIsNull() {
            addCriterion("member_count is null");
            return (Criteria) this;
        }

        public Criteria andMemberCountIsNotNull() {
            addCriterion("member_count is not null");
            return (Criteria) this;
        }

        public Criteria andMemberCountEqualTo(Integer value) {
            addCriterion("member_count =", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountNotEqualTo(Integer value) {
            addCriterion("member_count <>", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountGreaterThan(Integer value) {
            addCriterion("member_count >", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_count >=", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountLessThan(Integer value) {
            addCriterion("member_count <", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountLessThanOrEqualTo(Integer value) {
            addCriterion("member_count <=", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountIn(List<Integer> values) {
            addCriterion("member_count in", values, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountNotIn(List<Integer> values) {
            addCriterion("member_count not in", values, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountBetween(Integer value1, Integer value2) {
            addCriterion("member_count between", value1, value2, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountNotBetween(Integer value1, Integer value2) {
            addCriterion("member_count not between", value1, value2, "memberCount");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNull() {
            addCriterion("create_at is null");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNotNull() {
            addCriterion("create_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreateAtEqualTo(Date value) {
            addCriterion("create_at =", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotEqualTo(Date value) {
            addCriterion("create_at <>", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThan(Date value) {
            addCriterion("create_at >", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("create_at >=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThan(Date value) {
            addCriterion("create_at <", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThanOrEqualTo(Date value) {
            addCriterion("create_at <=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtIn(List<Date> values) {
            addCriterion("create_at in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotIn(List<Date> values) {
            addCriterion("create_at not in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtBetween(Date value1, Date value2) {
            addCriterion("create_at between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotBetween(Date value1, Date value2) {
            addCriterion("create_at not between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andHandleAtIsNull() {
            addCriterion("handle_at is null");
            return (Criteria) this;
        }

        public Criteria andHandleAtIsNotNull() {
            addCriterion("handle_at is not null");
            return (Criteria) this;
        }

        public Criteria andHandleAtEqualTo(Date value) {
            addCriterion("handle_at =", value, "handleAt");
            return (Criteria) this;
        }

        public Criteria andHandleAtNotEqualTo(Date value) {
            addCriterion("handle_at <>", value, "handleAt");
            return (Criteria) this;
        }

        public Criteria andHandleAtGreaterThan(Date value) {
            addCriterion("handle_at >", value, "handleAt");
            return (Criteria) this;
        }

        public Criteria andHandleAtGreaterThanOrEqualTo(Date value) {
            addCriterion("handle_at >=", value, "handleAt");
            return (Criteria) this;
        }

        public Criteria andHandleAtLessThan(Date value) {
            addCriterion("handle_at <", value, "handleAt");
            return (Criteria) this;
        }

        public Criteria andHandleAtLessThanOrEqualTo(Date value) {
            addCriterion("handle_at <=", value, "handleAt");
            return (Criteria) this;
        }

        public Criteria andHandleAtIn(List<Date> values) {
            addCriterion("handle_at in", values, "handleAt");
            return (Criteria) this;
        }

        public Criteria andHandleAtNotIn(List<Date> values) {
            addCriterion("handle_at not in", values, "handleAt");
            return (Criteria) this;
        }

        public Criteria andHandleAtBetween(Date value1, Date value2) {
            addCriterion("handle_at between", value1, value2, "handleAt");
            return (Criteria) this;
        }

        public Criteria andHandleAtNotBetween(Date value1, Date value2) {
            addCriterion("handle_at not between", value1, value2, "handleAt");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Boolean value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Boolean value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Boolean value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Boolean value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Boolean value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Boolean> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Boolean> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Boolean value1, Boolean value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("state not between", value1, value2, "state");
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