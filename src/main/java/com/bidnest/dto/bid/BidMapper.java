package com.bidnest.dto.bid;

import com.bidnest.model.Bid;
import com.bidnest.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BidMapper {

    Bid createDTOToBid(BidCreateDTO bidCreateDTO);

    @Mapping(source = "bidder", target = "bidderFullName", qualifiedByName = "userToFullName")
    BidResponseDTO bidToResponseDTO(Bid bid);

    @Named("userToFullName")
    static String userToFullName(User user) {
        return user.getLastName() == null ? user.getFirstName() : user.getFirstName() + " " + user.getLastName();
    }
}
