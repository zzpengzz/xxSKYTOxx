package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import domain.Grammer;
import domain.Production;
/*
 * 根据语法构造语法分析表
 * 先把分析表直接写死，进行实现一次
 * 
 */
public class GrammerService {
	//状态栈
	public static Stack<Integer> status = new Stack<Integer>();
	//符号栈
	public static Stack<String> symbol = new Stack<String>();
	//输入串
	public static List<String> sentences = new ArrayList<String>();
	public void getCode(String content) {
		//将句子找出
		String s ="[^ \\f\\n\\r\\t\\v\\#]+#{1}";
		Pattern pattern=Pattern.compile(s); 
		Matcher ma=pattern.matcher(content); 
	     while(ma.find()){ 
	    	 sentences.add(ma.group());
	     } 
	}
//	Action[sm, ai]: 当前状态sm面对输入符号ai时 采取的动作
//	(1) 移进 – sj (2) 归约 – rj (3) 接受 – acc (4) 报错 – 空白 / ‘出错’ / ‘error’
	public void runAnalysis() {
		/*对每一个输入串进行语法分析
		 * 判断每一个状态采取的动作
		 */
		for (String sentence : sentences) {
			int temp = 0;
			//初始化栈
			status.push(0);
			symbol.push("#");
			System.out.println("分析句子："+sentence);
			while(temp < sentence.length()) {
				//记录下当前符号
				String nowSymbol = "";
				char c = sentence.charAt(temp);
				nowSymbol = nowSymbol+c;
				//查action表
				Integer nextAction = Grammer.action.get(status.peek()).get(nowSymbol);
				System.out.print("当前状态"+status.peek()+'\t'+"下一个字符"+nowSymbol+'\t'+"下一个动作"+nextAction+'\n');
				if(nextAction == null) {
					//查到空值
					System.out.println(sentence+"不符合语法规则，分析结束");
					System.out.println("出错位置："+sentence.substring(0,temp+1));
					
					error(status.peek(), nowSymbol);
					System.out.println("--------------------------------------------------------------------");
					break;
				}
				if(nextAction>0 && nextAction!=10000) {
					//移进
					System.out.println("移进："+nowSymbol);
					status.push(nextAction);
					symbol.push(nowSymbol);
					temp++;
				}
				if(nextAction<0) {
					//规约
					int j =  Math.abs(nextAction);
					//取第j条产生式
					Production production = Grammer.productions.get(j);
					int t = production.getRight().length();
					/*
					 * 根据产生式进行规约
					 *	1、pop符号栈t次
					 *	2、pop状态栈t次
					 *	3、push符号栈产生式左部
					 *	4、查goto表，push查到的符号
					 */
					for(int i=0;i<t;i++) {
						status.pop();
						symbol.pop();
					}
					symbol.push(production.getLeft());
					//使用当前状态查goto表
					Integer s = Grammer.gotoTable.get(status.peek());
					if(s==null) {
						//查到空值
						System.out.println(sentence+"不符合语goto规则，分析结束");
						break;
					}
					status.push(s);
					System.out.print("goto"+s+'\n');
					System.out.println("规约："+production);
				}
				if(nextAction == 10000) {
					//acc 接受
					System.out.println(sentence+"分析成功，文法正确");
					System.out.println("--------------------------------------------------------------------");
					break;
				}
		
			}
			//清空栈
			status.clear();
			symbol.clear();
		}
	}
	public void error(Integer nowStatus,String nowSymbol) {
		if(nowStatus ==0) {
			if(nowSymbol.equals("+")||nowSymbol.equals("-")||nowSymbol.equals("*")||nowSymbol.equals("/")) {
				System.out.println("请在“+，-，*，/”之前加入“i”");
			}
			if(nowSymbol.equals(")")) {
				System.out.println("请在“）”之前加入“(i”");
			}
		}
		if(nowSymbol.equals("i")) {
			System.out.println("请不要输入连续的“i”");
		}
		if(nowStatus ==1) {
			
			if(nowSymbol.equals("(")||nowSymbol.equals(")")) {
				System.out.println("“i”后应该跟运算符！");
			}
		}
		if(nowStatus==2) {
			
			System.out.println("“(”后只能跟“i”");
			if(nowSymbol.equals("#")) {
				System.out.println("表达式不完整，尝试在“(”之后加入“i)”");
			}
		}
		if(nowStatus==3) {
			if(nowSymbol.equals("(")) {
				System.out.println("“i”后应该有运算符");
			}
		}
		if(nowStatus==4 ||nowStatus==5||nowStatus==6||nowStatus==7) {
			if(nowSymbol.equals("+")||nowSymbol.equals("-")||nowSymbol.equals("*")||nowSymbol.equals("/")) {
				System.out.println("请不要输入连续的运算符");
			}
			if(nowSymbol.equals(")")) {
				System.out.println("缺少“(i”");
			}
			if(nowSymbol.equals("#")) {
				System.out.println("表达式不完整，尝试在运算符之后加入“i”");
			}
		}
		if(nowStatus == 8 ||nowStatus == 9||nowStatus == 10||nowStatus == 11||nowStatus == 12||nowStatus == 13) {
			if(nowSymbol.equals("(")) {
				System.out.println("“(”前缺少运算符");
			}
		}
	}
	
}
