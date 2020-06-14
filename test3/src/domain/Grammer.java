package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 * 语法结构
 * 
 */
//非终结符：E，终结符：i，+，-，*，/,#
//产生式：
//S’	->E
//	E	->E+E
//	E	->E-E
//	E	->E*E
//	E	->E/E
//	E	->(E)
//	E	->i
import java.util.Set;
import java.util.TreeSet;
public class Grammer {
	//产生式
	public static List<Production> productions = new ArrayList<Production>();
	//终结符
	public static List<String> terminals = new ArrayList<String>();
	//非终结符
	public static List<String> nonTerminals = new ArrayList<String>();
	//语法分析表
	public static List<Map<String,Integer>> action = new ArrayList<Map<String,Integer>>();
	public static Map<Integer,Integer>  gotoTable = new HashMap<Integer, Integer>();
//	//项目集
//	public static List<Production> projects = new ArrayList<Production>();
	static {
		//产生式初始化
		productions.add(new Production("S'", "E"));
		productions.add(new Production("E", "E+E"));
		productions.add(new Production("E", "E-E"));
		productions.add(new Production("E", "E*E"));
		productions.add(new Production("E", "E/E"));
		productions.add(new Production("E", "(E)"));
		productions.add(new Production("E", "i"));
		//终结符初始化
		terminals.add("i");
		terminals.add("+");
		terminals.add("-");
		terminals.add("*");
		terminals.add("/");
		terminals.add("(");
		terminals.add(")");
		terminals.add("#");
		//非终结符初始化
		nonTerminals.add("E");
		//语法分析表
//		   //i,+,-,*,/,(,),#
//			{3,0,0,0,0,2,0,0},
//			{0,4,5,6,7,0,0,10000},
//			{3,0,0,0,0,2,0,0},
//			{0,-6,-6,-6,-6,0,-6,-6},
//			{3,0,0,0,0,2,0,0},
//			{3,0,0,0,0,2,0,0},
//			{3,0,0,0,0,2,0,0},
//			{3,0,0,0,0,2,0,0},
//			{0,4,5,6,7,0,13,0},
//			{0,-1,-1,6,7,0,-1,-1},
//			{0,-2,-2,6,7,0,-2,-2},
//			{0,-3,-3,-3,-3,0,-3,-3},
//			{0,-4,-4,-4,-4,0,-4,-4},
//			{0,-5,-5,-5,-5,0,-5,-5},
		Map<String,Integer> s0 = new HashMap<String, Integer>();
		s0.put("i",3);
		s0.put("(",2);
		action.add(s0);
		Map<String,Integer> s1 = new HashMap<String, Integer>();
		s1.put("+", 4);
		s1.put("-", 5);
		s1.put("*", 6);
		s1.put("/", 7);
		s1.put("#", 10000);
		action.add(s1);
		Map<String,Integer> s2 = new HashMap<String, Integer>();
		s2.put("i", 3);
		s2.put("(", 2);
		action.add(s2);
		Map<String,Integer> s3 = new HashMap<String, Integer>();
		s3.put("+", -6);
		s3.put("-", -6);
		s3.put("*", -6);
		s3.put("/", -6);
		s3.put(")", -6);
		s3.put("#", -6);
		action.add(s3);
		Map<String,Integer> s4 = new HashMap<String, Integer>();
		s4.put("i", 3);
		s4.put("(", 2);
		action.add(s4);
		action.add(s4);
		action.add(s4);
		action.add(s4);
		Map<String,Integer> s8 = new HashMap<String, Integer>();
		s8.put("+", 4);
		s8.put("-", 5);
		s8.put("*", 6);
		s8.put("/", 7);
		s8.put(")", 13);
		action.add(s8);
		Map<String,Integer> s9 = new HashMap<String, Integer>();
		s9.put("+", -1);
		s9.put("-", -1);
		s9.put("*", 6);
		s9.put("/", 7);
		s9.put(")", -1);
		s9.put("#", -1);
		action.add(s9);
		Map<String,Integer> s10 = new HashMap<String, Integer>();
		s10.put("+", -2);
		s10.put("-", -2);
		s10.put("*", 6);
		s10.put("/", 7);
		s10.put(")", -2);
		s10.put("#", -2);
		action.add(s10);
		Map<String,Integer> s11 = new HashMap<String, Integer>();
		s11.put("+", -3);
		s11.put("-", -3);
		s11.put("*", -3);
		s11.put("/", -3);
		s11.put(")", -3);
		s11.put("#", -3);
		action.add(s11);
		Map<String,Integer> s12 = new HashMap<String, Integer>();
		s12.put("+", -4);
		s12.put("-", -4);
		s12.put("*", -4);
		s12.put("/", -4);
		s12.put(")", -4);
		s12.put("#", -4);
		action.add(s12);
		Map<String,Integer> s13 = new HashMap<String, Integer>();
		s13.put("+", -5);
		s13.put("-", -5);
		s13.put("*", -5);
		s13.put("/", -5);
		s13.put(")", -5);
		s13.put("#", -5);
		action.add(s13);
		
		gotoTable.put(0, 1);
		gotoTable.put(2, 8);
		gotoTable.put(4, 9);
		gotoTable.put(5, 10);
		gotoTable.put(6, 11);
		gotoTable.put(7, 12);
	
	}
}
	/*	TODO:自动生成LR（0）分析表
	 * 	1、生成项目集
	 * 	2、生成项目规范族
	 * 	3、构造活前缀DFA
	 * 	4、生成分析表
	 */
//	public static void makeAnaTable() {
//		//求出项目集
//		for (Production production : productions) {
//			//项目个数
//			int number = production.getRight().length() +1;
//			for(int i=0; i<number;i++) {
//				StringBuilder sb = new StringBuilder(production.getRight());
//				sb.insert(i, ".");
//				Production project = new Production(production.getLeft(), sb.toString());
//				projects.add(project);
//			}
//		}
//		/*
//		 *	通过闭包函数构造DFA和分析表
//		 *	
//		 */
//		
//		
//	}
//	//闭包函数
//	public static List<Production> closure(Production p) {
//		/*
//		 * 一个项目的闭包包含自身，以及.后面非终结符的所有.开头的项目
//		 */
//		List<Production> s = new ArrayList<Production>();
//
//		s.add(p);
//		
//		int i = p.getLeft().indexOf(".");
//		if(i != p.getLeft().length()) {
//			char c = p.getLeft().charAt(i+1);
//			String nextSymbol ="";
//			nextSymbol = nextSymbol+c;
//			
//			if(nonTerminals.contains(nextSymbol)) {
//				//.以后的符号为非终结符；
//				boolean flag = true;
//				for (Production project : projects) {
//					if(project.getLeft().equals( nextSymbol)&& project.getRight().charAt(0) == '.') {
//						for (Production production : s) {
//							if(production.getLeft().equals(project.getLeft())&&production.getRight().equals(project.getRight()))
//								flag =false;
//						}
//						if(flag) {
//							s.add(project);
//							
//							
//						}
//						if(!flag) {
//							flag = true;
//						}
//						
//					}
//				}
//			}
//		}
//		return s;
//	}
//}
