import java.util.*;
public class test2 {
    public static void re(Stack stack1, String str,char[] word,int i)//用来替换栈顶元素
    {
        stack1.pop();
        char[] temp=str.toCharArray();
        for(int j=temp.length-1;j>=0;j--)
        {
            String s=String.valueOf(temp[j]);
            stack1.push(s);
            System.out.print(stack1.peek());
        }
        System.out.printf("\t\t\t\t");
       for(int k=i;k<word.length;k++)
        {
            System.out.print(word[k]);
        }
        System.out.printf("\t\t\t"+word[i]);
        System.out.printf("\n");
    }

    public static void main(String[] args)
    {
        System.out.println(" 请输入符号串\n");
        Scanner s=new Scanner(System.in);
        String test1 = s.nextLine()+"#";
        char[] test2 = test1.toCharArray();//输入的字符串
        String E="TA";//LL(1)文法
        String []A={"+TA","-TA "};
        String T="FB";
        String []B={"*FB","/FB"};
        String []F={"(E)","i"};
        System.out.println(" 符号栈\t\t\t输入串\t\t当前输入符号\n");
        Stack stack1=new Stack();
        stack1.push("#");
        stack1.push("E");//初始化
        for(int i=0;i<test2.length;)//匹配判断栈顶元素是否符合输入符号
        {
            if("E".equals(stack1.peek()))
            {
                if(test2[i]=='('||test2[i]=='i')
                {
                    re(stack1,E,test2,i);
                }
                else
                {
                    System.out.println("语法错误");
                    break;
                }//System.out.print(stack1.peek());
            }

           else if("A".equals(stack1.peek()))
            {
                if(test2[i]==')'||test2[i]=='#')
                {
                    System.out.print(stack1.peek()+"\t\t\t\t"+test2[i]+"\t\t\t"+test2[i]+"\n");
                    stack1.pop();
                }
                else if( test2[i]=='+')
                {
                    re(stack1,A[0],test2,i);
                    i++;
                    stack1.pop();
                }
                else if( test2[i]=='-')
                {
                    re(stack1,A[0],test2,i);
                    i++;
                    stack1.pop();
                }
                else
                {
                    System.out.print(stack1.peek()+"\t\t\t\t"+test2[i]+"\t\t\t"+test2[i]+"\n");
                    System.out.println("语法错误7");
                    break;
                }
            }


            else  if("T".equals(stack1.peek()))
            {

                if(test2[i]=='('||test2[i]=='i')
                {

                    re(stack1,T,test2,i);
                }
                else
                {

                    System.out.println("语法错误6");
                    break;
                }
            }
            else  if("B".equals(stack1.peek()))
            {
                if(test2[i]=='+'||test2[i]=='-'||test2[i]==')'||test2[i]=='#')
                {
                    System.out.printf(stack1.peek()+"\t\t\t\t");
                    for(int k=i;k<test2.length;k++)
                    {
                        System.out.print(test2[k]);
                    }
                    System.out.printf("\t\t\t"+test2[i]+"\n");
                    stack1.pop();
                }

                else if(test2[i]=='*' )
                {
                    re(stack1,B[0],test2,i);
                    i++;
                    stack1.pop();
                }
                else if(test2[i]=='/' )
                {
                    re(stack1,B[1],test2,i);
                    i++;
                    stack1.pop();
                }
                else
                {
                    System.out.println("语法错误5");
                    break;
                }

            }

            else  if("F".equals(stack1.peek()))
            {
                if(test2[i]=='(')
                {
                    re(stack1,F[0],test2,i);
                    i++;
                    stack1.pop();
                }
                else if(test2[i]=='i' )
                {
                    re(stack1,F[1],test2,i);
                    i++;
                    stack1.pop();
                }
                else
                {
                    System.out.println("语法错误4");
                    break;
                }

            }
            else  if(")".equals(stack1.peek()))
            {
                if(test2[i]==')')
                {
                    System.out.print(stack1.peek()+"\t\t\t\t"+test2[i]+"\t\t\t"+test2[i]+"\n");
                    stack1.pop();
                    i++;
                }
                else
                {

                    System.out.println("语法错误3");
                    break;
                }
            }
            else  if("#".equals(stack1.peek()))
            {
                if(test2[i]=='#')
                {
                    System.out.print(stack1.peek()+"\t\t\t\t"+test2[i]+"\n");
                    System.out.println("分析成功 \n");
                }
               else
                {
                    System.out.println("语法错误2");
                    break;
                }
                stack1.pop();
               // break;
            }
            else
            {
                System.out.println("语法错误1");
                break;
            }
        }
    }
}
