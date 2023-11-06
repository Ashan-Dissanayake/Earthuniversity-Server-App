package lk.earth.earthuniversity.controller;


import lk.earth.earthuniversity.dao.InvoiceDao;
import lk.earth.earthuniversity.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@CrossOrigin
@RestController
@RequestMapping(value = "/invoices")
public class InvcoiceController {

    @Autowired
    private InvoiceDao invoicedao;

    @GetMapping(produces = "application/json")
    public List<Invoice> get(@RequestParam HashMap<String, String> params) {

        List<Invoice> invoices = this.invoicedao.findAll();

        if(params.isEmpty())  return invoices;

        String customer = params.get("customer");
        String statusid= params.get("statusid");


        Stream<Invoice> istream = invoices.stream();

        if(customer!=null) istream = istream.filter(i -> i.getCustomer().getName().equals(customer));
        if(statusid!=null) istream = istream.filter(i -> i.getInvoicestatus().getId()==Integer.parseInt(statusid));


        return istream.collect(Collectors.toList());

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Invoice invoice) {
        HashMap<String, String> response = new HashMap<>();
        String errors = "";

        if(invoicedao.findInvoiceByCode(invoice.getName())!=null)
            errors = errors+"<br> Existing Code";

        for (Invoiceitem i : invoice.getInvoiceitem()) {
            i.setInvoice(invoice);
            //System.out.println("ppppppp-" + i.getItem().getName() + "-" + i.getLinetotal() );
        }

        invoicedao.save(invoice);

        response.put("id", String.valueOf(invoice.getId()));
        response.put("url", "/invoices/" + invoice.getId());
        response.put("errors", errors);

        return response;

    }


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> update(@RequestBody Invoice invoice) {

        HashMap<String, String> response = new HashMap<>();
        String errors = "";
        System.out.println("Update");

        Invoice extInvoice = invoicedao.findInvoiceByCode(invoice.getName());


        if (extInvoice != null) {

            try {
                extInvoice.getInvoiceitem().clear();
                invoice.getInvoiceitem().forEach(newinvoiceitem -> {
                    newinvoiceitem.setInvoice(extInvoice);
                    extInvoice.getInvoiceitem().add(newinvoiceitem);
                    newinvoiceitem.setInvoice(extInvoice);
                });

               BeanUtils.copyProperties(invoice, extInvoice, "id","invoiceitem","name");

                invoicedao.save(extInvoice); // Save the updated extUser object

                response.put("id", String.valueOf(invoice.getId()));
                response.put("url", "/invoices/" + invoice.getId());
                response.put("errors", errors);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return response;
    }


    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable String code){

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Invoice inv1 = invoicedao.findInvoiceByCode(code);

        if(inv1==null)
            errors = errors+"<br> Invoice Does Not Existed";

        if(errors=="") invoicedao.delete(inv1);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("code",String.valueOf(code));
        responce.put("url","/code/"+code);
        responce.put("errors",errors);

        return responce;
    }



}


