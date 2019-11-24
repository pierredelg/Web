<%--
  Created by IntelliJ IDEA.
  User: delgranp
  Date: 19/11/2019
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Login</title>
    <%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page errorPage="ErrorPage.jsp" %>
    <%@ page import="java.sql.*" %>
    <%@ page import="java.time.Duration" %>
    <%@ page import="java.time.Instant" %>
    <%@ page import="java.time.LocalDateTime" %>
    <%@ page import="java.time.ZoneOffset" %>
</head>
<body>
<%

    String urlPsql = config.getServletContext().getInitParameter("url");
    String nomPsql = config.getServletContext().getInitParameter("login");
    String mdpPsql = config.getServletContext().getInitParameter("mdp");
    String driverPsql = config.getServletContext().getInitParameter("driver");
    String login = request.getParameter("login");
    String mdpUser = request.getParameter("mdp");


    Class.forName(driverPsql);
    Connection con = DriverManager.getConnection(urlPsql,nomPsql,mdpPsql);
    String query = "Select * from login where nom=? and mdp=?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setString(1,login);
    ps.setString(2,mdpUser);

    ResultSet rs = ps.executeQuery();
    //Présent dans la base
    if(rs.next()) {

        Timestamp ts = rs.getTimestamp("dat");
        LocalDateTime dateBDD = null;
        if( ts != null ) {
            dateBDD = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
        }

        String ip = rs.getString("ip");

        String query2 = "update login set dat = ? where nom=(select nom from login where nom = ? and mdp= ? and ip= ?);";
        PreparedStatement ps2 = con.prepareStatement(query2);

        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = new Timestamp(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli());
        ps2.setTimestamp(1,timestamp);
        ps2.setString(2, login);
        ps2.setString(3, mdpUser);
        ps2.setString(4, request.getRemoteAddr());
        System.out.println(ps2);

        ps2.executeUpdate();
        long seconds = 0;
        Duration duration;
        if(dateBDD != null) {
            duration = Duration.between(dateBDD, localDateTime);
             seconds = duration.getSeconds();
        }
        int MINUTES_PER_HOUR = 60;
        int SECONDS_PER_MINUTE = 60;
        int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
        long heures = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secondes = (seconds % SECONDS_PER_MINUTE);


%>
<p>Bonjour Mr <%= login %>, votre derniere connexion date de <%= heures%>>:<%=minutes%>:<%=secondes%> de la machine <%= ip %></p>
<%}
    //1ere connexion
    else{
%>
<p>Bonjour Mr  <%=login%>, bienvenue sur ce site</p>
<%
        String query3 = "insert into login values( ?,?,?,?);";
        PreparedStatement ps3 = con.prepareStatement(query3);
        ps3.setString(1,login);
        ps3.setString(2,mdpUser);
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = new Timestamp(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli());
        ps3.setTimestamp(3,timestamp);
        ps3.setString(4,request.getRemoteAddr());
        System.out.println(ps3);
        ps3.executeUpdate();
    }
    con.close();
%>
</body>
</html>
