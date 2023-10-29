package com.bidnest.dto.auction;

import com.bidnest.model.auction.Auction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuctionMapper {

    @Mapping(target = "images", ignore = true)
    Auction createDTOToEntity(AuctionCreateDTO auctionCreateDTO);

    void updateEntity(@MappingTarget Auction auction, AuctionUpdateDTO updateDTO);

    AuctionResponseDTO entityToResponseDTO(Auction auction);
}
