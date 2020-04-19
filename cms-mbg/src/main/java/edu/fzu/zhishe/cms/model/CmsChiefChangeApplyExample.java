package edu.fzu.zhishe.cms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CmsChiefChangeApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CmsChiefChangeApplyExample() {
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

        public Criteria andOldChiefIdIsNull() {
            addCriterion("old_chief_id is null");
            return (Criteria) this;
        }

        public Criteria andOldChiefIdIsNotNull() {
            addCriterion("old_chief_id is not null");
            return (Criteria) this;
        }

        public Criteria andOldChiefIdEqualTo(Integer value) {
            addCriterion("old_chief_id =", value, "oldChiefId");
            return (Criteria) this;
        }

        public Criteria andOldChiefIdNotEqualTo(Integer value) {
            addCriterion("old_chief_id <>", value, "oldChiefId");
            return (Criteria) this;
        }

        public Criteria andOldChiefIdGreaterThan(Integer value) {
            addCriterion("old_chief_id >", value, "oldChiefId");
            return (Criteria) this;
        }

        public Criteria andOldChiefIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("old_chief_id >=", value, "oldChiefId");
            return (Criteria) this;
        }

        public Criteria andOldChiefIdLessThan(Integer value) {
            addCriterion("old_chief_id <", value, "oldChiefId");
            return (Criteria) this;
        }

        public Criteria andOldChiefIdLessThanOrEqualTo(Integer value) {
            addCriterion("old_chief_id <=", value, "oldChiefId");
            return (Criteria) this;
        }

        public Criteria andOldChiefIdIn(List<Integer> values) {
            addCriterion("old_chief_id in", values, "oldChiefId");
            return (Criteria) this;
        }

        public Criteria andOldChiefIdNotIn(List<Integer> values) {
            addCriterion("old_chief_id not in", values, "oldChiefId");
            return (Criteria) this;
        }

        public Criteria andOldChiefIdBetween(Integer value1, Integer value2) {
            addCriterion("old_chief_id between", value1, value2, "oldChiefId");
            return (Criteria) this;
        }

        public Criteria andOldChiefIdNotBetween(Integer value1, Integer value2) {
            addCriterion("old_chief_id not between", value1, value2, "oldChiefId");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdIsNull() {
            addCriterion("new_chief_id is null");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdIsNotNull() {
            addCriterion("new_chief_id is not null");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdEqualTo(Integer value) {
            addCriterion("new_chief_id =", value, "newChiefId");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdNotEqualTo(Integer value) {
            addCriterion("new_chief_id <>", value, "newChiefId");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdGreaterThan(Integer value) {
            addCriterion("new_chief_id >", value, "newChiefId");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("new_chief_id >=", value, "newChiefId");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdLessThan(Integer value) {
            addCriterion("new_chief_id <", value, "newChiefId");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdLessThanOrEqualTo(Integer value) {
            addCriterion("new_chief_id <=", value, "newChiefId");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdIn(List<Integer> values) {
            addCriterion("new_chief_id in", values, "newChiefId");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdNotIn(List<Integer> values) {
            addCriterion("new_chief_id not in", values, "newChiefId");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdBetween(Integer value1, Integer value2) {
            addCriterion("new_chief_id between", value1, value2, "newChiefId");
            return (Criteria) this;
        }

        public Criteria andNewChiefIdNotBetween(Integer value1, Integer value2) {
            addCriterion("new_chief_id not between", value1, value2, "newChiefId");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
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

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
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