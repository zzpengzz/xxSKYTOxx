package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import domain.Grammer;
import domain.Production;
/*
 * �����﷨�����﷨������
 * �Ȱѷ�����ֱ��д��������ʵ��һ��
 * 
 */
public class GrammerService {
	//״̬ջ
	public static Stack<Integer> status = new Stack<Integer>();
	//����ջ
	public static Stack<String> symbol = new Stack<String>();
	//���봮
	public static List<String> sentences = new ArrayList<String>();
	public void getCode(String content) {
		//�������ҳ�
		String s ="[^ \\f\\n\\r\\t\\v\\#]+#{1}";
		Pattern pattern=Pattern.compile(s); 
		Matcher ma=pattern.matcher(content); 
	     while(ma.find()){ 
	    	 sentences.add(ma.group());
	     } 
	}
//	Action[sm, ai]: ��ǰ״̬sm����������aiʱ ��ȡ�Ķ���
//	(1) �ƽ� �C sj (2) ��Լ �C rj (3) ���� �C acc (4) ���� �C �հ� / ������ / ��error��
	public void runAnalysis() {
		/*��ÿһ�����봮�����﷨����
		 * �ж�ÿһ��״̬��ȡ�Ķ���
		 */
		for (String sentence : sentences) {
			int temp = 0;
			//��ʼ��ջ
			status.push(0);
			symbol.push("#");
			System.out.println("�������ӣ�"+sentence);
			while(temp < sentence.length()) {
				//��¼�µ�ǰ����
				String nowSymbol = "";
				char c = sentence.charAt(temp);
				nowSymbol = nowSymbol+c;
				//��action��
				Integer nextAction = Grammer.action.get(status.peek()).get(nowSymbol);
				System.out.print("��ǰ״̬"+status.peek()+'\t'+"��һ���ַ�"+nowSymbol+'\t'+"��һ������"+nextAction+'\n');
				if(nextAction == null) {
					//�鵽��ֵ
					System.out.println(sentence+"�������﷨���򣬷�������");
					System.out.println("����λ�ã�"+sentence.substring(0,temp+1));
					
					error(status.peek(), nowSymbol);
					System.out.println("--------------------------------------------------------------------");
					break;
				}
				if(nextAction>0 && nextAction!=10000) {
					//�ƽ�
					System.out.println("�ƽ���"+nowSymbol);
					status.push(nextAction);
					symbol.push(nowSymbol);
					temp++;
				}
				if(nextAction<0) {
					//��Լ
					int j =  Math.abs(nextAction);
					//ȡ��j������ʽ
					Production production = Grammer.productions.get(j);
					int t = production.getRight().length();
					/*
					 * ���ݲ���ʽ���й�Լ
					 *	1��pop����ջt��
					 *	2��pop״̬ջt��
					 *	3��push����ջ����ʽ��
					 *	4����goto��push�鵽�ķ���
					 */
					for(int i=0;i<t;i++) {
						status.pop();
						symbol.pop();
					}
					symbol.push(production.getLeft());
					//ʹ�õ�ǰ״̬��goto��
					Integer s = Grammer.gotoTable.get(status.peek());
					if(s==null) {
						//�鵽��ֵ
						System.out.println(sentence+"��������goto���򣬷�������");
						break;
					}
					status.push(s);
					System.out.print("goto"+s+'\n');
					System.out.println("��Լ��"+production);
				}
				if(nextAction == 10000) {
					//acc ����
					System.out.println(sentence+"�����ɹ����ķ���ȷ");
					System.out.println("--------------------------------------------------------------------");
					break;
				}
		
			}
			//���ջ
			status.clear();
			symbol.clear();
		}
	}
	public void error(Integer nowStatus,String nowSymbol) {
		if(nowStatus ==0) {
			if(nowSymbol.equals("+")||nowSymbol.equals("-")||nowSymbol.equals("*")||nowSymbol.equals("/")) {
				System.out.println("���ڡ�+��-��*��/��֮ǰ���롰i��");
			}
			if(nowSymbol.equals(")")) {
				System.out.println("���ڡ�����֮ǰ���롰(i��");
			}
		}
		if(nowSymbol.equals("i")) {
			System.out.println("�벻Ҫ���������ġ�i��");
		}
		if(nowStatus ==1) {
			
			if(nowSymbol.equals("(")||nowSymbol.equals(")")) {
				System.out.println("��i����Ӧ�ø��������");
			}
		}
		if(nowStatus==2) {
			
			System.out.println("��(����ֻ�ܸ���i��");
			if(nowSymbol.equals("#")) {
				System.out.println("���ʽ�������������ڡ�(��֮����롰i)��");
			}
		}
		if(nowStatus==3) {
			if(nowSymbol.equals("(")) {
				System.out.println("��i����Ӧ���������");
			}
		}
		if(nowStatus==4 ||nowStatus==5||nowStatus==6||nowStatus==7) {
			if(nowSymbol.equals("+")||nowSymbol.equals("-")||nowSymbol.equals("*")||nowSymbol.equals("/")) {
				System.out.println("�벻Ҫ���������������");
			}
			if(nowSymbol.equals(")")) {
				System.out.println("ȱ�١�(i��");
			}
			if(nowSymbol.equals("#")) {
				System.out.println("���ʽ�������������������֮����롰i��");
			}
		}
		if(nowStatus == 8 ||nowStatus == 9||nowStatus == 10||nowStatus == 11||nowStatus == 12||nowStatus == 13) {
			if(nowSymbol.equals("(")) {
				System.out.println("��(��ǰȱ�������");
			}
		}
	}
	
}
