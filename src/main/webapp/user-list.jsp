<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
 <title>User Management Application</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<div style="border:1 solid black; margin-left:10%; margin-top:80px; width:80%;">
 <center>
  <h1>User Management</h1>
        <h2>
         <a href="new" style="text-decoration:none; color:white;" class="btn btn-primary">Add New User</a>
         &nbsp;&nbsp;&nbsp;
         <a href="list" style="text-decoration:none; color:white;" class="btn btn-primary">List All Users</a>
         
         
         
        </h2>
 </center>
 <center><h2>List of Users</h2></center>
    <div align="center">
        <table border="1" cellpadding="5" class="table" style="margin-top:30px;">
            
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">contact</th>
                <th scope="col">Actions</th>
            </tr>
            <c:forEach items="${listUser}" var="user" >
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.contact}" /></td>
                    <td>
                     <a href="edit?id=<c:out value='${user.id}' />"><i class="fa-regular fa-pen-to-square"></i></a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="delete?id=<c:out value='${user.id}' />"><i class="fa-solid fa-trash"></i></a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="view?id=<c:out value='${user.id}' />"><i class="fa-solid fa-eye"></i></a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div> 
    </div> 
</body>
</html>