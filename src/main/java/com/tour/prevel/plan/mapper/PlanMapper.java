package com.tour.prevel.plan.mapper;

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
}
