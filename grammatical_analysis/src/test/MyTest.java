package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import domain.Grammer;
import domain.Production;
import service.GrammerService;

public class MyTest {
	//��������
	static String fileContent;
	static {
		//��ȡ�ļ�����
	     FileInputStream fis = null;
	        try {
	            fis = new FileInputStream("test"); 
	            StringBuilder sb = new StringBuilder();
	            int temp = 0;
	            //��temp����-1ʱ����ʾ�Ѿ������ļ���β��ֹͣ��ȡ
	            while ((temp = fis.read()) != -1) {
	                sb.append((char) temp);
	            }
	            fileContent = sb.toString();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                //��֤�˼�ʹ�����쳣�����Ҳ��ر�������
	                if (fis != null) {
	                    fis.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}

/*	@Test
	public void Test1(){
//		System.out.println(GrammerService.action[0][0]);
//		System.out.println(GrammerService.gotoTable[0][0]);
//
	}*/
	@Test
	public void Test2(){
		GrammerService gs = new GrammerService();
		gs.getCode(fileContent);
		gs.runAnalysis();
	}
//	@Test
//	public void Test3(){
//		Grammer.makeAnaTable();
//		for (Production project : Grammer.projects) {
//			System.out.println(project);
//		}
//	}

}
