<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Messages</title>
<link rel="stylesheet" href="messages_style.css">
<body>
            <% 
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            
            String username=(String)session.getAttribute("username");
                if(username == null){
                	response.sendRedirect("LoginPage.jsp");
                }
            %>
    <header id="header">
        <div class="header-container">
            <h1 class="header-heading">
                Welcome <%= username %>!
            </h1>
            <div class="button">
            <a href="ActiveMessages.jsp">Messages</a>
            <a href="Logout">Logout</a>
            </div>
        </div>
    </header>
    <main id="main">
        <table class="archive-table">
            <caption>
                <h1>Archived Messages</h1>
            </caption>
            <tbody>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Message</th>
                    <th>Unarchive</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="message" items="${archiveMessages}">
                    <tr class="display-row" >
                        <td>${message.name}</td>
                        <td>${message.email}</td>
                        <td>${message.message}</td>
                        <td><a href="restoreFromActive?id=${message.id}">Unarchive</a></td>
                        <td><a href="deleteArchivedMessage?id=${message.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
</body>
</html>