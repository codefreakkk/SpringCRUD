package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import service.UserService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/index"})
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public @ResponseBody String addata(@ModelAttribute User user) {
        int row = this.userService.addData(user);
        String response = row + "";
        return response;
    }

    @RequestMapping(value = "/getdata", method = RequestMethod.GET)
    public @ResponseBody String getdata() {
        List<User> user = this.userService.getAllUsersData();

        String response = "";
        for(User data : user) {
            response += "<tr>" +
                    "                <th scope=\"row\">"+data.id+"</th>\n" +
                    "                <td>"+data.name+"</td>\n" +
                    "                <td>"+data.address+"</td>\n" +
                    "                <td style='cursor: pointer' data-uid='"+data.id+"' id='del'>delete</td>\n" +
                    "            </tr>";
        }

        return response;
    }
}
