package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.service.IBidListService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;


@Controller
public class BidListController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BidListController.class);

    private final IBidListService bidListService;

    public BidListController(IBidListService bidListService) {
        this.bidListService = bidListService;
    }

    @GetMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("bidLists", bidListService.getBidLists());
        LOGGER.info("BidLists successfully found - code : {}", HttpStatus.OK);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm( @ModelAttribute("bidList") BidListDto bidListDto) {
        LOGGER.info("BidListForm successfully found - code : {}", HttpStatus.OK);
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidListDto bidListDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
        bidListService.addBidList(bidListDto);
        model.addAttribute("bidLists", bidListService.getBidLists());
        LOGGER.info("BidList successfully saved - code : {}", HttpStatus.FOUND);
        return "redirect:/bidList/list";
        }
        LOGGER.error("BidList cannot be saved - code : {}", HttpStatus.OK);
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("bidList", bidListService.getBidById(id));
        LOGGER.info("BidList UpdateForm successfully found - code : {}", HttpStatus.OK);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid  @ModelAttribute("bidList") BidListDto bidListDto,
                             BindingResult result, Model model) {
         if (result.hasErrors()) {
             LOGGER.error("BidList cannot be updated - code : {}", HttpStatus.OK);
            return "bidList/update";

        }
        bidListDto.setId(id);
        bidListDto.setAccount(bidListDto.getAccount());
        bidListDto.setType(bidListDto.getType());
        bidListDto.setBidQuantity(bidListDto.getBidQuantity());
        bidListService.addBidList(bidListDto);
        model.addAttribute("bidLists", bidListService.getBidLists());
        LOGGER.info("BidList successfully updated - code : {}", HttpStatus.FOUND);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBidById(id);
        model.addAttribute("bidLists", bidListService.getBidLists());
        LOGGER.info("BidList successfully delete - code : {}", HttpStatus.FOUND);
        return "redirect:/bidList/list";
    }
}
