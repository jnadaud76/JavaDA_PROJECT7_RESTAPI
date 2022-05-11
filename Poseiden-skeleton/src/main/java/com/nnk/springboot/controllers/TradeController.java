package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.service.ITradeService;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("API for trade CRUD operations.")
@Controller
public class TradeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);

    private final ITradeService tradeService;

    public TradeController(ITradeService tradeService) {
        this.tradeService = tradeService;
    }

    @ApiOperation(value = "Retrieving all trade.")
    @GetMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeService.getTrades());
        LOGGER.info("Trades successfully found - code : {}", HttpStatus.OK);
        return "trade/list";
    }

    @ApiOperation(value = "Showing trade creation form.")
    @GetMapping("/trade/add")
    public String addTradeForm(@ModelAttribute("trade") TradeDto tradeDto) {
        LOGGER.info("TradeForm successfully found - code : {}", HttpStatus.OK);
        return "trade/add";
    }

    @ApiOperation(value = "Adding one trade after validation.")
    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") TradeDto tradeDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.addTrade(tradeDto);
            model.addAttribute("trades", tradeService.getTrades());
            LOGGER.info("Trade successfully saved - code : {}", HttpStatus.FOUND);
            return "redirect:/trade/list";
        }
        LOGGER.error("Trade cannot be saved - code : {}", HttpStatus.OK);
        return "trade/add";
    }

    @ApiOperation(value = "Showing trade updating form.")
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("trade", tradeService.getTradeById(id));
        LOGGER.info("Trade UpdateForm successfully found - code : {}", HttpStatus.OK);
        return "trade/update";
    }

    @ApiOperation(value = "Updating one trade after validation.")
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid @ModelAttribute("trade") TradeDto tradeDto,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            LOGGER.error("Trade cannot be updated - code : {}", HttpStatus.OK);
            return "trade/update";
        }
        tradeDto.setId(tradeDto.getId());
        tradeDto.setAccount(tradeDto.getAccount());
        tradeDto.setType(tradeDto.getType());
        tradeDto.setBuyQuantity(tradeDto.getBuyQuantity());
        tradeService.addTrade(tradeDto);
        model.addAttribute("trades", tradeService.getTrades());
        LOGGER.info("Trade successfully updated - code : {}", HttpStatus.FOUND);
        return "redirect:/trade/list";
    }

    @ApiOperation(value = "Deleting one trade.")
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.deleteTradeById(id);
        model.addAttribute("trades", tradeService.getTrades());
        LOGGER.info("Trade successfully delete - code : {}", HttpStatus.FOUND);
        return "redirect:/trade/list";
    }
}
