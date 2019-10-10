/*   1:    */ package com.demons.shiro.util;
/*   3:    */ import java.io.BufferedReader;
/*   4:    */ import java.io.File;
/*   5:    */ import java.io.FileInputStream;
/*   6:    */ import java.io.FileOutputStream;
/*   7:    */ import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
/*   9:    */ 
/*  10:    */ public class FileUtil
/*  11:    */ {
/*  12:    */   public static boolean delAllFile(String folderPath)
/*  13:    */   {
/*  14: 20 */     boolean flag = false;
/*  15: 21 */     File file = new File(folderPath);
/*  16: 22 */     if (!file.exists()) {
/*  17: 23 */       return flag;
/*  18:    */     }
/*  19: 25 */     if (!file.isDirectory()) {
/*  20: 26 */       return flag;
/*  21:    */     }
/*  22: 28 */     String[] tempList = file.list();
/*  23: 29 */     File temp = null;
/*  24: 30 */     for (int i = 0; i < tempList.length; i++)
/*  25:    */     {
/*  26: 31 */       if (folderPath.endsWith(File.separator)) {
/*  27: 32 */         temp = new File(folderPath + tempList[i]);
/*  28:    */       } else {
/*  29: 34 */         temp = new File(folderPath + File.separator + tempList[i]);
/*  30:    */       }
/*  31: 36 */       if (temp.isFile()) {
/*  32: 37 */         temp.delete();
/*  33:    */       }
/*  34: 39 */       if (temp.isDirectory())
/*  35:    */       {
/*  36: 40 */         delAllFile(folderPath + "/" + tempList[i]);
/*  37: 41 */         delFolder(folderPath + "/" + tempList[i]);
/*  38: 42 */         flag = true;
/*  39:    */       }
/*  40:    */     }
/*  41: 45 */     return flag;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public static boolean delFile(String filePath)
/*  45:    */   {
/*  46: 54 */     boolean flag = false;
/*  47: 55 */     File file = new File(filePath);
/*  48: 56 */     if (!file.exists()) {
/*  49: 57 */       return flag;
/*  50:    */     }
/*  51: 59 */     flag = new File(filePath).delete();
/*  52: 60 */     return flag;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public static boolean delFolder(String folderPath)
/*  56:    */   {
/*  57:    */     try
/*  58:    */     {
/*  59: 70 */       delAllFile(folderPath);
/*  60: 71 */       String filePath = folderPath;
/*  61: 72 */       filePath = filePath.toString();
/*  62: 73 */       File myFilePath = new File(filePath);
/*  63: 74 */       myFilePath.delete();
/*  64:    */     }
/*  65:    */     catch (Exception e)
/*  66:    */     {
/*  67: 76 */       e.printStackTrace();
/*  68: 77 */       return false;
/*  69:    */     }
/*  70: 79 */     return true;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public static String readFile(String curfile)
/*  74:    */   {
/*  75: 89 */     File f = new File(curfile);
/*  76:    */     try
/*  77:    */     {
/*  78: 92 */       if (!f.exists()) {
/*  79: 92 */         throw new Exception();
/*  80:    */       }
/*  81: 93 */       FileReader cf = new FileReader(curfile);
/*  82: 94 */       BufferedReader is = new BufferedReader(cf);
/*  83: 95 */       String filecontent = "";
/*  84: 96 */       String str = is.readLine();
/*  85: 97 */       while (str != null)
/*  86:    */       {
/*  87: 98 */         filecontent = filecontent + str;
/*  88: 99 */         str = is.readLine();
/*  89:100 */         if (str != null) {
/*  90:101 */           filecontent = filecontent + "\n";
/*  91:    */         }
/*  92:    */       }
/*  93:103 */       is.close();
/*  94:104 */       cf.close();
/*  95:105 */       return filecontent;
/*  96:    */     }
/*  97:    */     catch (Exception e)
/*  98:    */     {
/*  99:109 */       System.err.println("不能读属性文件: " + curfile + " \n" + e.getMessage());
/* 100:    */     }
/* 101:110 */     return "";
/* 102:    */   }
/* 103:    */   
/* 104:    */   public static String getFileExt(String filePathName)
/* 105:    */   {
/* 106:121 */     int pos = 0;
/* 107:122 */     pos = filePathName.lastIndexOf('.');
/* 108:123 */     if (pos != -1) {
/* 109:124 */       return filePathName.substring(pos + 1, filePathName.length());
/* 110:    */     }
/* 111:126 */     return "";
/* 112:    */   }
/* 113:    */   
/* 114:    */   public static int getFileSize(String filename)
/* 115:    */   {
/* 116:    */     try
/* 117:    */     {
/* 118:139 */       File fl = new File(filename);
/* 119:140 */       return (int)fl.length();
/* 120:    */     }
/* 121:    */     catch (Exception e) {}
/* 122:145 */     return 0;
/* 123:    */   }
/* 124:    */   
/* 125:    */   public static boolean copyFile(String srcPath, String destPath)
/* 126:    */   {
/* 127:    */     try
/* 128:    */     {
/* 129:159 */       File fl = new File(srcPath);
/* 130:160 */       int length = (int)fl.length();
/* 131:161 */       FileInputStream is = new FileInputStream(srcPath);
/* 132:162 */       FileOutputStream os = new FileOutputStream(destPath);
/* 133:163 */       byte[] b = new byte[length];
/* 134:164 */       is.read(b);
/* 135:165 */       os.write(b);
/* 136:166 */       is.close();
/* 137:167 */       os.close();
/* 138:168 */       return true;
/* 139:    */     }
/* 140:    */     catch (Exception e) {}
/* 141:172 */     return false;
/* 142:    */   }

/**
 * 根据单元格的值属性来获取excel单元格的值。 日期默认返回格式自己根据需求定，这里返回yyyy-MM-dd类型和HH:mm这两种。
 * 
 * @param cell
 * @return
 */
public static String getCellValue(Cell cell) {
    String result = "";
    if (cell != null) {
        switch (cell.getCellType()) {
        // 数字类型 +日期类型
        case HSSFCell.CELL_TYPE_NUMERIC:
            if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                SimpleDateFormat sdf = null;
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                        .getBuiltinFormat("h:mm")) {
                    sdf = new SimpleDateFormat("HH:mm");
                } else {// 日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }
                Date date = cell.getDateCellValue();
                result = sdf.format(date);
            } else if (cell.getCellStyle().getDataFormat() == 58) {
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                double value = cell.getNumericCellValue();
                Date date = org.apache.poi.ss.usermodel.DateUtil
                        .getJavaDate(value);
                result = sdf.format(date);
            } else {
                DecimalFormat df = new DecimalFormat();
                df.setGroupingUsed(false);
                result = String.valueOf(df.format(cell
                        .getNumericCellValue()));
            }
            break;
        // String类型
        case HSSFCell.CELL_TYPE_STRING:
            result = String.valueOf(cell.getStringCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            result = "";
        default:
            result = "";
            break;
        }
    }

    return result;
}

}

