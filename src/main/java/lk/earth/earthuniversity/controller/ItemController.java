package lk.earth.earthuniversity.controller;


import lk.earth.earthuniversity.dao.InvoiceDao;
import lk.earth.earthuniversity.dao.ItemDao;
import lk.earth.earthuniversity.entity.Invoice;
import lk.earth.earthuniversity.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    private ItemDao itemdao;

    @GetMapping(produces = "application/json")
    public List<Item> get() {

        List<Item> items = this.itemdao.findAll();
        return items;

    }

}


