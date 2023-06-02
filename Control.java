package RestAPI.Fetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class Control {
    @Autowired
    Operation o;
    @Autowired
    //@Lazy //is annontation ko autowired ke sath use karme par error araha tha pata nhi q
    Information i;
    //Information i=new Information(); //ya to object banalo ya to autowired use krlo taki vo ioc container ke duvaaraa object initialize karde

    @GetMapping("/fetch")
    public ArrayList<Information> allInf(){
        Iterable<Information> all = o.findAll();
        ArrayList<Information> ii= (ArrayList<Information>) all;
        System.out.println(all);
        System.out.println(o.hashCode());
        return ii;
    }
    @GetMapping("/one/{in}")
    public Information getOne(@PathVariable("in") int i){
        Optional<Information> byId = o.findById(i);
        Information info = byId.get();
        return info;
    }
    @PostMapping("/post")
    public Information create(@RequestBody Information in){
        this.i.setAmount(in.getAmount());
        this.i.setName(in.getName());
        //o.save(in);
        o.save(this.i);
        return this.i;
    }
    @PutMapping("/update/{u}")
    public Information update(@RequestBody Information inf,@PathVariable("u") int id){
        Optional<Information> byId = o.findById(id);
        Information ifor = byId.get();
        ifor.setName(inf.getName());
        ifor.setAmount(inf.getAmount());
        o.save(ifor);
        return ifor;
    }
    @DeleteMapping("/delete/{u1}")
    public String update(@PathVariable("u1") int id){
        Optional<Information> byId = o.findById(id);
        Information ifor = byId.get();
        o.delete(ifor);
        return "deleted successfully";
    }
    @PostMapping("/file")
    public ResponseEntity<String> upfile(@RequestParam("a") MultipartFile multipartFile) throws IOException {
        byte[] bytes = multipartFile.getBytes();
        FileOutputStream fos=new FileOutputStream("C:\\Users\\VIVEK PAL\\Downloads\\"+multipartFile.getOriginalFilename());
        i.setFile(bytes);
        System.out.println(i.getFile());
        o.save(i);
        return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/").path(multipartFile.getOriginalFilename()).toUriString());
    }
}
