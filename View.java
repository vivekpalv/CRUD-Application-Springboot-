package RestAPI.Fetch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class View{
    @Autowired
    Operation o;
    @GetMapping("vive")
    public String vi(Model m){
        m.addAttribute("name","Pal");
        m.addAttribute("bool",false);
        m.addAttribute("v","i");
        ArrayList<Integer> ali=new ArrayList<>();
        ali.add(1);
        ali.add(2);
        ali.add(13);
        m.addAttribute("vi",ali);
        return "vivek";
    }
    @PostMapping("/vi")
    public String post(Model m,@ModelAttribute Information i,@ModelAttribute Home h,@RequestParam(value = "fi",required = false) MultipartFile file) throws IOException {
        i.setHome(h);
        if (file!=null) {
            System.out.println("a");
            String originalFilename = file.getOriginalFilename();
            FileOutputStream fo=new FileOutputStream("C:\\Users\\VIVEK PAL\\Downloads\\Fetch\\Fetch\\src\\main\\resources\\static\\"+ originalFilename);
            fo.write(file.getBytes());
            System.out.println(file.getBytes());
            i.setFile(file.getBytes());
            String uriString = ServletUriComponentsBuilder.fromCurrentContextPath().path("/").path(file.getOriginalFilename()).toUriString();
            System.out.println(uriString);
            m.addAttribute("img",uriString);
            fo.close();
        }
        o.save(i);
        return "Create";
    }
    @GetMapping("/home")
    public String home(){
        return "vivekpal";
    }
    @PostMapping("/update")
    public String getFetch(@RequestParam(value = "i" ,defaultValue = "1") int i, @RequestParam(value = "fi",required = false) MultipartFile file, Model m, @ModelAttribute Information ii, @ModelAttribute Home home) throws IOException {
        Optional<Information> byId = o.findById(i);
        Information information = byId.get();
        if (file!=null){
            byte[] bytes = file.getBytes();
            information.setFile(bytes);
        }else System.out.println("file is null");
        if (i>1) {
            information.setHome(home);
            information.setName(ii.getName());
            information.setNameType(ii.getNameType());
            information.setAmount(ii.getAmount());
            information.setQuantity(ii.getQuantity());
            m.addAttribute("name", information);
            o.save(information);
            String uriString = ServletUriComponentsBuilder.fromCurrentContextPath().path("/").path(file.getOriginalFilename()).toUriString();
            m.addAttribute("img",uriString);
        }
        return "Update";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(value = "a",defaultValue = "0")int ii,Model m){
        Optional<Information> byId = o.findById(ii);
        if (ii>0) {
            Information information = byId.get();
            o.delete(information);
        } else System.out.println("ii == 0");
        return "Delete";
    }
    @GetMapping("/get")
    public String fetch(@RequestParam(value = "fet",defaultValue = "0")int i,Model m) throws IOException {
        Optional<Information> byId = o.findById(i);
        if (i!=0) {
            Information information = byId.get();
            m.addAttribute("a", information);
            byte[] file = information.getFile();
            FileOutputStream fos=new FileOutputStream("C:\\Users\\VIVEK PAL\\Downloads\\Fetch\\Fetch\\src\\main\\resources\\static\\"+"a1.png");
            fos.write(file);
            String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/").path("a1.png").toUriString();
            m.addAttribute("img1",uri);
        }
        return "Get";
    }
}
