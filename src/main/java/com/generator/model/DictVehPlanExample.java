package com.generator.model;

import java.util.ArrayList;
import java.util.List;

public class DictVehPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DictVehPlanExample() {
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

        public Criteria andRouteCodeIsNull() {
            addCriterion("ROUTE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRouteCodeIsNotNull() {
            addCriterion("ROUTE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRouteCodeEqualTo(String value) {
            addCriterion("ROUTE_CODE =", value, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteCodeNotEqualTo(String value) {
            addCriterion("ROUTE_CODE <>", value, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteCodeGreaterThan(String value) {
            addCriterion("ROUTE_CODE >", value, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ROUTE_CODE >=", value, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteCodeLessThan(String value) {
            addCriterion("ROUTE_CODE <", value, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteCodeLessThanOrEqualTo(String value) {
            addCriterion("ROUTE_CODE <=", value, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteCodeLike(String value) {
            addCriterion("ROUTE_CODE like", value, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteCodeNotLike(String value) {
            addCriterion("ROUTE_CODE not like", value, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteCodeIn(List<String> values) {
            addCriterion("ROUTE_CODE in", values, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteCodeNotIn(List<String> values) {
            addCriterion("ROUTE_CODE not in", values, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteCodeBetween(String value1, String value2) {
            addCriterion("ROUTE_CODE between", value1, value2, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteCodeNotBetween(String value1, String value2) {
            addCriterion("ROUTE_CODE not between", value1, value2, "routeCode");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownIsNull() {
            addCriterion("ROUTE_UPDOWN is null");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownIsNotNull() {
            addCriterion("ROUTE_UPDOWN is not null");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownEqualTo(Short value) {
            addCriterion("ROUTE_UPDOWN =", value, "routeUpdown");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownNotEqualTo(Short value) {
            addCriterion("ROUTE_UPDOWN <>", value, "routeUpdown");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownGreaterThan(Short value) {
            addCriterion("ROUTE_UPDOWN >", value, "routeUpdown");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownGreaterThanOrEqualTo(Short value) {
            addCriterion("ROUTE_UPDOWN >=", value, "routeUpdown");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownLessThan(Short value) {
            addCriterion("ROUTE_UPDOWN <", value, "routeUpdown");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownLessThanOrEqualTo(Short value) {
            addCriterion("ROUTE_UPDOWN <=", value, "routeUpdown");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownIn(List<Short> values) {
            addCriterion("ROUTE_UPDOWN in", values, "routeUpdown");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownNotIn(List<Short> values) {
            addCriterion("ROUTE_UPDOWN not in", values, "routeUpdown");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownBetween(Short value1, Short value2) {
            addCriterion("ROUTE_UPDOWN between", value1, value2, "routeUpdown");
            return (Criteria) this;
        }

        public Criteria andRouteUpdownNotBetween(Short value1, Short value2) {
            addCriterion("ROUTE_UPDOWN not between", value1, value2, "routeUpdown");
            return (Criteria) this;
        }

        public Criteria andRunDateIsNull() {
            addCriterion("RUN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRunDateIsNotNull() {
            addCriterion("RUN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRunDateEqualTo(String value) {
            addCriterion("RUN_DATE =", value, "runDate");
            return (Criteria) this;
        }

        public Criteria andRunDateNotEqualTo(String value) {
            addCriterion("RUN_DATE <>", value, "runDate");
            return (Criteria) this;
        }

        public Criteria andRunDateGreaterThan(String value) {
            addCriterion("RUN_DATE >", value, "runDate");
            return (Criteria) this;
        }

        public Criteria andRunDateGreaterThanOrEqualTo(String value) {
            addCriterion("RUN_DATE >=", value, "runDate");
            return (Criteria) this;
        }

        public Criteria andRunDateLessThan(String value) {
            addCriterion("RUN_DATE <", value, "runDate");
            return (Criteria) this;
        }

        public Criteria andRunDateLessThanOrEqualTo(String value) {
            addCriterion("RUN_DATE <=", value, "runDate");
            return (Criteria) this;
        }

        public Criteria andRunDateLike(String value) {
            addCriterion("RUN_DATE like", value, "runDate");
            return (Criteria) this;
        }

        public Criteria andRunDateNotLike(String value) {
            addCriterion("RUN_DATE not like", value, "runDate");
            return (Criteria) this;
        }

        public Criteria andRunDateIn(List<String> values) {
            addCriterion("RUN_DATE in", values, "runDate");
            return (Criteria) this;
        }

        public Criteria andRunDateNotIn(List<String> values) {
            addCriterion("RUN_DATE not in", values, "runDate");
            return (Criteria) this;
        }

        public Criteria andRunDateBetween(String value1, String value2) {
            addCriterion("RUN_DATE between", value1, value2, "runDate");
            return (Criteria) this;
        }

        public Criteria andRunDateNotBetween(String value1, String value2) {
            addCriterion("RUN_DATE not between", value1, value2, "runDate");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNull() {
            addCriterion("PLAN_ID is null");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNotNull() {
            addCriterion("PLAN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIdEqualTo(String value) {
            addCriterion("PLAN_ID =", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotEqualTo(String value) {
            addCriterion("PLAN_ID <>", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThan(String value) {
            addCriterion("PLAN_ID >", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_ID >=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThan(String value) {
            addCriterion("PLAN_ID <", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThanOrEqualTo(String value) {
            addCriterion("PLAN_ID <=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLike(String value) {
            addCriterion("PLAN_ID like", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotLike(String value) {
            addCriterion("PLAN_ID not like", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIn(List<String> values) {
            addCriterion("PLAN_ID in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotIn(List<String> values) {
            addCriterion("PLAN_ID not in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdBetween(String value1, String value2) {
            addCriterion("PLAN_ID between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotBetween(String value1, String value2) {
            addCriterion("PLAN_ID not between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andVehCodeIsNull() {
            addCriterion("VEH_CODE is null");
            return (Criteria) this;
        }

        public Criteria andVehCodeIsNotNull() {
            addCriterion("VEH_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andVehCodeEqualTo(String value) {
            addCriterion("VEH_CODE =", value, "vehCode");
            return (Criteria) this;
        }

        public Criteria andVehCodeNotEqualTo(String value) {
            addCriterion("VEH_CODE <>", value, "vehCode");
            return (Criteria) this;
        }

        public Criteria andVehCodeGreaterThan(String value) {
            addCriterion("VEH_CODE >", value, "vehCode");
            return (Criteria) this;
        }

        public Criteria andVehCodeGreaterThanOrEqualTo(String value) {
            addCriterion("VEH_CODE >=", value, "vehCode");
            return (Criteria) this;
        }

        public Criteria andVehCodeLessThan(String value) {
            addCriterion("VEH_CODE <", value, "vehCode");
            return (Criteria) this;
        }

        public Criteria andVehCodeLessThanOrEqualTo(String value) {
            addCriterion("VEH_CODE <=", value, "vehCode");
            return (Criteria) this;
        }

        public Criteria andVehCodeLike(String value) {
            addCriterion("VEH_CODE like", value, "vehCode");
            return (Criteria) this;
        }

        public Criteria andVehCodeNotLike(String value) {
            addCriterion("VEH_CODE not like", value, "vehCode");
            return (Criteria) this;
        }

        public Criteria andVehCodeIn(List<String> values) {
            addCriterion("VEH_CODE in", values, "vehCode");
            return (Criteria) this;
        }

        public Criteria andVehCodeNotIn(List<String> values) {
            addCriterion("VEH_CODE not in", values, "vehCode");
            return (Criteria) this;
        }

        public Criteria andVehCodeBetween(String value1, String value2) {
            addCriterion("VEH_CODE between", value1, value2, "vehCode");
            return (Criteria) this;
        }

        public Criteria andVehCodeNotBetween(String value1, String value2) {
            addCriterion("VEH_CODE not between", value1, value2, "vehCode");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeIsNull() {
            addCriterion("LEAVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeIsNotNull() {
            addCriterion("LEAVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeEqualTo(String value) {
            addCriterion("LEAVE_TIME =", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeNotEqualTo(String value) {
            addCriterion("LEAVE_TIME <>", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeGreaterThan(String value) {
            addCriterion("LEAVE_TIME >", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LEAVE_TIME >=", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeLessThan(String value) {
            addCriterion("LEAVE_TIME <", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeLessThanOrEqualTo(String value) {
            addCriterion("LEAVE_TIME <=", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeLike(String value) {
            addCriterion("LEAVE_TIME like", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeNotLike(String value) {
            addCriterion("LEAVE_TIME not like", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeIn(List<String> values) {
            addCriterion("LEAVE_TIME in", values, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeNotIn(List<String> values) {
            addCriterion("LEAVE_TIME not in", values, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeBetween(String value1, String value2) {
            addCriterion("LEAVE_TIME between", value1, value2, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeNotBetween(String value1, String value2) {
            addCriterion("LEAVE_TIME not between", value1, value2, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeIsNull() {
            addCriterion("REAL_LEAVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeIsNotNull() {
            addCriterion("REAL_LEAVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeEqualTo(String value) {
            addCriterion("REAL_LEAVE_TIME =", value, "realLeaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeNotEqualTo(String value) {
            addCriterion("REAL_LEAVE_TIME <>", value, "realLeaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeGreaterThan(String value) {
            addCriterion("REAL_LEAVE_TIME >", value, "realLeaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REAL_LEAVE_TIME >=", value, "realLeaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeLessThan(String value) {
            addCriterion("REAL_LEAVE_TIME <", value, "realLeaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeLessThanOrEqualTo(String value) {
            addCriterion("REAL_LEAVE_TIME <=", value, "realLeaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeLike(String value) {
            addCriterion("REAL_LEAVE_TIME like", value, "realLeaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeNotLike(String value) {
            addCriterion("REAL_LEAVE_TIME not like", value, "realLeaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeIn(List<String> values) {
            addCriterion("REAL_LEAVE_TIME in", values, "realLeaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeNotIn(List<String> values) {
            addCriterion("REAL_LEAVE_TIME not in", values, "realLeaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeBetween(String value1, String value2) {
            addCriterion("REAL_LEAVE_TIME between", value1, value2, "realLeaveTime");
            return (Criteria) this;
        }

        public Criteria andRealLeaveTimeNotBetween(String value1, String value2) {
            addCriterion("REAL_LEAVE_TIME not between", value1, value2, "realLeaveTime");
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