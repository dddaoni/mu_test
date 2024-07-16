<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<style>
    table {
        border-collapse: collapse;
        width: 300px;
    }
    th {
        background-color: lightgray;
    }
    th, td {
        border: 1px solid black;
        text-align: center;
    }
</style>
<body>
<h1>Main Page</h1>
<button id="btn1">카테고리 별 최저가격 브랜드와 상품 가격,총액 조회</button>
<hr>
<div id="result">
    <table id="resultTable">
        <thead>
            <tr>
                <th>카테고리</th>
                <th>브랜드</th>
                <th>가격</th>
            </tr>
        </thead>
        <tbody>
        <tr>
        </tr>
        </tbody>
    </table>
</div>
</body>

<script type="text/javascript">
    $(document).ready(function() {
        $("#btn1").click(function() {
            $.ajax({
                url: "/api/product/getLowestCategoryList",
                method: "GET",
                dataType: "json",
                error: function(xhr, status, error) {
                    console.error("AJAX Error: ", status, error);
                },
                success: function(res) {
                    console.log(res);
                    let table = $("#resultTable tbody");
                    table.empty();

                    $.each(res.brandProductList, function(index, product) {
                        let row = $("<tr></tr>");
                        row.append($("<td></td>").text(product.category));
                        row.append($("<td></td>").text(product.brand));
                        row.append($("<td></td>").text(product.price));
                        table.append(row);
                    });

                    let row = $("<tr></tr>");
                    row.append($("<td colspan='2'></td>").text("총액"));
                    row.append($("<td></td>").text(res.totalPrice));
                    table.append(row);
                }
            });
        });
    });
</script>

</html>
