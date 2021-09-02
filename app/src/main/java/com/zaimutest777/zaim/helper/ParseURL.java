package com.zaimutest777.zaim.helper;

import java.net.URI;


/**
 * Created by Ilham Shihnazarow on 14/7/2021
 */

public class ParseURL {

    /** /docs/books/tutorial/index.html */
    public static String getPathUrl(String url) throws Exception {
        URI aURL = new URI(url);
        String path = aURL.getPath();

        return path;

    }
    /** //example.com */
    public static String getHostUrl(String url) throws Exception {
        URI aURL = new URI(url);
        String domain = aURL.getHost();

        return "https://"+domain;
    }
    /** //name=networking */
    public static String getQueryUrl(String url) throws Exception {
        URI aURL = new URI(url);
        String query = aURL.getHost();

        return  query;
    }
    /** //80 */
    public static String getPortUrl(String url) throws Exception {
        URI aURL = new URI(url);
        String port = aURL.getHost();

        return port;
    }

    /** //example.com:80 */
    public static String getAuthorityUrl(String url) throws Exception {
        URI aURL = new URI(url);
        String authority = aURL.getHost();

        return authority;
    }
}