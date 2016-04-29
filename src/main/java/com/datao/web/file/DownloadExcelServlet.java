package com.datao.web.file;

import com.datao.dao.BuyDAO;
import com.datao.pojo.Book;
import com.datao.pojo.User;
import com.datao.web.BaseServlet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by 海涛 on 2016/3/26.
 * 下载已经购买的书单
 */
@WebServlet("/downexcel.do")
public class DownloadExcelServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment;fileName=\"data.xls\"");

        Workbook workbook = new HSSFWorkbook();

        //建立一个安全的页签名
        String sheetName = WorkbookUtil.createSafeSheetName("书籍清单");
        Sheet sheet = workbook.createSheet(sheetName);

        //建立表格第一行标题
        Row firstRow = sheet.createRow(0);
        Cell cell0 = firstRow.createCell(0);
        cell0.setCellValue("书籍名称");
        firstRow.createCell(1).setCellValue("作者");
        firstRow.createCell(2).setCellValue("出版社");
        firstRow.createCell(3).setCellValue("类型");
        firstRow.createCell(4).setCellValue("一句书评");
        //查询所有书籍
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Book> bookList = new BuyDAO().findByStuId(user.getId());

        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            Row newRow = sheet.createRow(i + 1);
            newRow.createCell(0).setCellValue(book.getTitle());
            newRow.createCell(1).setCellValue(book.getAuthor());
            newRow.createCell(2).setCellValue(book.getPublishing());
            newRow.createCell(3).setCellValue(book.getType());
            newRow.createCell(4).setCellValue(book.getWhy());
        }

        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);

        outputStream.flush();
        outputStream.close();
        outputStream.close();
    }
}
