package com.tour.prevel.plan.mapper;

import com.tour.prevel.plan.dto.PlanHistoryResponse;
import com.tour.prevel.plan.dto.RecommandPlanResponse;
import com.tour.prevel.plan.domain.Plan;
import com.tour.prevel.plan.domain.PlanImage;
import com.tour.prevel.plan.domain.Schedule;
import com.tour.prevel.plan.dto.ScheduleResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanMapper {

    @IterableMapping(qualifiedByName = "toRestaurantResponse")
    List<ScheduleResponse> toScheduleResponseList(List<Schedule> scheduleList);

    @Named("toRestaurantResponse")
    @Mapping(target = "order", source = "schedule.scheduleOrder")
    @Mapping(target = "date", source = "schedule.scheduleDate")
    ScheduleResponse toScheduleResponse(Schedule schedule);

    @IterableMapping(qualifiedByName = "toRecomandPlanResponse")
    List<RecommandPlanResponse> toRecomandPlanResponseList(List<Plan> recommandPlanList);

    @Named("toRecomandPlanResponse")
    @Mapping(target = "url", source = "plan.planImage", qualifiedByName = "thumnailToImage")
    RecommandPlanResponse toRecomandPlanResponse(Plan plan);

    @Named("thumnailToImage")
    default String thumnailToImage(PlanImage thumnail) {
        return thumnail.getUrl();
    }

    @IterableMapping(qualifiedByName = "toPlanHistoryResponse")
    List<PlanHistoryResponse> toPlanHistoryResponseList(List<Plan> planList);

    @Named("toPlanHistoryResponse")
    @Mapping(target = "url", source = "plan.planImage", qualifiedByName = "thumnailToImage")
    PlanHistoryResponse toPlanHistoryResponse(Plan plan);
}
