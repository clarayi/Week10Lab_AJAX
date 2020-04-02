<%-- 
    Document   : notes.jsp
    Created on : Mar 2, 2020, 2:48:21 PM
    Author     : 810783
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Notes!</h1>
        <table>
            <col width="250">
            <col width="150">
            <tr>
                <td>Date Created</td>
                <td>Title</td>
            </tr>
            <c:forEach var="oneNote" items="${noteList}" varStatus="status">
                <form method="POST">
                    <input type="hidden" name="action" value="editClicked=${oneNote.noteid}"/>
                    <tr>
                        <td>${oneNote.datecreated}</td>
                        <td>${oneNote.title}</td>
                        <td><input type="submit" value="Edit"/></td>
                    </tr>
                </form>    
            </c:forEach>
        </table><br>
        <h2>${whatToDo} Note</h2>
        <c:if test="${whatToDo eq 'Edit'}">
            <form method="POST">
            <input type="hidden" name="action" value="delete=${editNoteID}"/>
            <input type="submit" value="Delete note"/><br><br>
            </form>
        </c:if>
        <form method="POST" id="form2">
            <c:if test="${whatToDo eq 'Add'}">
                <input type="hidden" name="action" value="add"/>
            </c:if>
            <c:if test="${whatToDo eq 'Edit'}">
                <input type="hidden" name="action" value="edit=${editNoteID}"/>
            </c:if>
            <input type="text" placeholder="Title" value="${titleBox}" name="inputTitle"/><br>
            <textarea rows="7" cols="30" form="form2" name="inputContents">${textareaBox}</textarea>
            <input type="submit" value="${whatToDo}"/>
        </form>
    </body>
</html>
