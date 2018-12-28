/**
 *  软件版权：SUNLEI
 *  系统名称：java8
 *  文件名称：TestFile.java
 *  版本变更记录（可选）：修改日期2017年8月31日  下午3:10:43，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/** 
 * @Description:
 * <p>创建日期：2017年8月31日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TestFile {
	@Test
	public void pathTest(){
        Path path = Paths.get("~");
        System.out.println(path);
        System.out.println(path.getNameCount());
        System.out.println(path.getRoot());
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        System.out.println(absolutePath.getNameCount());
        System.out.println(absolutePath.getFileSystem());
        //依次打印父路径
        absolutePath.forEach(name->System.out.println(name));
    }
	@Test
	public void filesTest() throws FileNotFoundException, IOException{
        //复制文件
        Files.copy(Paths.get("abc.txt"), new FileOutputStream("abc2.txt"));
        //判断是否隐藏文件
        System.out.println(Files.isHidden(Paths.get("abc.txt")));
        //读取文件的所有行到list
        List<String> lines = Files.readAllLines(Paths.get("abc.txt"));
        lines.forEach (line -> System.out.println(line));
        //文件大小
        System.out.println(Files.size(Paths.get("abc.txt")));
        //向文件里写入东西
        List<String> poem = new ArrayList<String>();
        poem.add("锐捷网络");
        poem.addAll(lines);
        Files.write(Paths.get("testFile/path.txt"), poem, Charset.forName("utf-8"));
        //列出文件目录下的所有文件(不递归)
        Files.list(Paths.get("/")).forEach(Name -> System.out.println(Name));
        //打印所有文件到控制台
        Files.lines(Paths.get("abc.txt"),Charset.forName("utf-8")).forEach(line -> System.out.println(line));
        //查看存储空间
        FileStore fs = Files.getFileStore(Paths.get("/"));
        System.out.println(fs.getTotalSpace());
        
    }
}
