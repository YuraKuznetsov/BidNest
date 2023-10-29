package com.bidnest.service.auction;

import com.bidnest.dto.auction.AuctionCreateDTO;
import com.bidnest.dto.auction.AuctionMapper;
import com.bidnest.dto.auction.AuctionResponseDTO;
import com.bidnest.dto.auction.AuctionUpdateDTO;
import com.bidnest.exception.ItemNotFoundException;
import com.bidnest.model.auction.Auction;
import com.bidnest.model.auction.Image;
import com.bidnest.model.user.User;
import com.bidnest.repository.AuctionRepository;
import com.bidnest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final UserService userService;
    private final ImageService imageService;
    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    public Auction findById(Long id) {
        return auctionRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Auction not found"));
    }

    public AuctionResponseDTO getAuctionResponseDTOById(Long id) {
        return auctionMapper.entityToResponseDTO(findById(id));
    }

    public AuctionResponseDTO createAuction(AuctionCreateDTO createDTO) {
        Auction auction = auctionMapper.createDTOToEntity(createDTO);
        User seller = userService.getCurrentUser();
        seller.addAuction(auction);
        uploadAuctionImages(auction, createDTO.getImages());

        return auctionMapper.entityToResponseDTO(auctionRepository.save(auction));
    }

    private void uploadAuctionImages(Auction auction, MultipartFile[] imageFiles) {
        for (MultipartFile imageFile : imageFiles) {
            Image image = imageService.uploadImageFile(auction.getId(), imageFile);
            auction.addImage(image);
        }
    }

    public void updateAuction(AuctionUpdateDTO updateDTO) {
        Auction auction = findById(updateDTO.getId());
        auctionMapper.updateEntity(auction, updateDTO);
        auctionRepository.save(auction);
    }
}
