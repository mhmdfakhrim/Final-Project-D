/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UCPWEBSERVICE.UCP.controller;

import UCPWEBSERVICE.UCP.model.entity.Daftarujian;
import UCPWEBSERVICE.UCP.model.jpa.DaftarujianJpaController;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ThinkPad
 */

@RestController 
@CrossOrigin
@RequestMapping("/liatucp")
public class UcpController {
    Daftarujian duj = new Daftarujian();
    DaftarujianJpaController djc = new DaftarujianJpaController();
    
    @GetMapping
    public List<Daftarujian> getData(){
        List<Daftarujian> data = new ArrayList<>();
        try{
            data = djc.findDaftarujianEntities();
        
        }catch(Exception e){}
        return data;
    }
    
    @GetMapping("/{Id}")
    public List<Daftarujian> getNilaiEntities (@PathVariable("Id") int id){
        List<Daftarujian> dataa = new ArrayList<>();
        try{
            duj = djc.findDaftarujian(id);
            dataa.add(duj);
        }catch(Exception e){}
        return dataa;
    }
    @PostMapping //untuk menambahkan data
    public String insertData(HttpEntity<String> requestdata){
        String message = "Selamat anda berhasil";
        Daftarujian daftar = new Daftarujian{};
        try{
            String json_receive = requestdata.getBody(); 
            ObjectMapper map = new ObjectMapper(); 
            daftar = map.readValue(json_receive, Daftarujian.class);
            djc.create(daftar);
            
        }catch(Exception e){
        message = "gagal menambahkan";
        }
        return message;
    }
    @PutMapping
        public String updateData(HttpEntity<String> requestdata){
        String message = "Sukses MengUpdate";
        Daftarujian daftar = null;
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            daftar = map.readValue(json_receive, Daftarujian.class);
            djc.edit(daftar);
            
        }catch(Exception e){
        message = "gagal mengupdate";
        }
        return message;
    }
        @DeleteMapping
        public String deleteData(HttpEntity<String> requestdata){
        String message = "Sukses Menghapus";
        Daftarujian daftar = null;
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            daftar = map.readValue(json_receive, Daftarujian.class);
            djc.destroy(daftar.getId());
            
        }catch(Exception e){
        message = "gagal menghapus";
        }
        return message;
    }

}
