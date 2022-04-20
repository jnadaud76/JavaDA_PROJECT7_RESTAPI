package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.util.IConversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidListService implements IBidListService{

    @Autowired
    BidListRepository bidListRepository;

    @Autowired
    IConversion conversion;

   public List<BidListDto> getBidLists() {
       List<BidList> bidLists = bidListRepository.findAll();
       List<BidListDto> bidListFullDtos = new ArrayList<>();
       for (BidList b : bidLists) {
           BidListDto bidListFullDto = conversion.bidListToBidListDto(b);
           bidListFullDtos.add(bidListFullDto);
       }
    return bidListFullDtos;

   }

   public void addBidList(BidList bidList) {
     bidListRepository.save(bidList);

   }

   public BidListDto getBidById(Integer id) {
       if(bidListRepository.existsById(id)){
           return conversion.bidListToBidListDto(bidListRepository.getById(id));

       } else {
           throw new IllegalArgumentException("Invalid bid Id:" + id);
       }

   }

   public void deleteBidById(Integer id) {
       if(bidListRepository.existsById(id)){
           bidListRepository.delete(bidListRepository.getById(id));
       } else {
           throw new IllegalArgumentException("Invalid bid Id:" + id);
       }
   }



}
