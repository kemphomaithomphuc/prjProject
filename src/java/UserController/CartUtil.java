/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import Models.DTO.CartItem;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mainh
 */
public class CartUtil {
    public Cookie getCookiesByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookiesArr = request.getCookies();
        if (cookiesArr != null) {
            for (Cookie cookie : cookiesArr) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }
    public HashMap<String, CartItem> getCartFromCookie(Cookie cookieCart) {
       HashMap<String, CartItem> cart = new HashMap();
       String mobileId, mobileName, description;
       int quantity, yearOfProduction;
       float price;
       boolean notSale;
       Base64.Decoder base64Decoder = Base64.getDecoder();
       String encodedString = new String(base64Decoder.decode(cookieCart.getValue().getBytes()));
       String[] stringArray = encodedString.split("\\|");
       for (String stringItem : stringArray) {
           String[] attribute = stringItem.split(",");
           mobileId = attribute[0].trim();
           description = attribute[1].trim();
           price = Float.parseFloat(attribute[2].trim());
           mobileName = attribute[3].trim();
           yearOfProduction = Integer.parseInt(attribute[4].trim());
           quantity = Integer.parseInt(attribute[5].trim());
           notSale = Boolean.parseBoolean(attribute[6].trim());
           CartItem cartItem = new CartItem(mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);
           cart.put(mobileId, cartItem);
       }
       return cart;
    }
public Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] arrCookies = request.getCookies();
        if (arrCookies != null) {
            for (Cookie cookie : arrCookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }
    public void saveCartToCookie(HttpServletRequest request, 
            HttpServletResponse response, String strItemsInCart) {
        String cookieName = "Cart";
        Cookie cookieCart = getCookieByName(request, cookieName);
        if (cookieCart != null) {
            cookieCart.setValue(strItemsInCart);
        } else {
            cookieCart = new Cookie(cookieName, strItemsInCart);
        }
        cookieCart.setMaxAge(30);
        response.addCookie(cookieCart);
    }
    public String convertCartToString(List<CartItem> itemsList) {
        StringBuilder strItemsInCart = new StringBuilder();
        for (CartItem item : itemsList) {
            strItemsInCart.append(item + "|");
        }
        Base64.Encoder base64Encoder = Base64.getEncoder();
        String encodedString = base64Encoder.encodeToString(strItemsInCart.toString().getBytes());
        return encodedString;
    }
}
