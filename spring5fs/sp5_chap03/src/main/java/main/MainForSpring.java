package main;

import config.AppConf1;
import config.AppConf2;
import config.AppCtx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MainForSpring {
    private static ApplicationContext ctx = null;

    public static void main(String[] args) throws IOException {
        ctx = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("명령어를 입력해주세요.");
            String command = reader.readLine();
            if (command.equalsIgnoreCase("exit")){
                System.out.println("종료합니다.");
                break;
            }else if(command.startsWith("new")){
                processNewCommand(command.split(" "));
            }else if(command.startsWith("edit")){
                processEditCommand(command.split(" "));
            }else if(command.equals("list")){
                processListCommand();
            }else if(command.startsWith("info")){
                processInfoCommand(command.split(" "));
            }else if(command.equals("version")){
                processVersionCommand();
            }
        }
        printHelp();
    }
    private static void processNewCommand(String[] args){
        if(args.length != 5){
            printHelp();
            return;
        }
        MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(args[1]);
        req.setName(args[2]);
        req.setPassword(args[3]);
        req.setConfirmedPassword(args[4]);
        if(!req.isPasswordEqualToConfirmedPassword()){
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }
        try{
            regSvc.regist(req);
            System.out.println("등록되었습니다. \n");
        }catch(AlreadyExistingMemberException e){
            System.out.println("이미 등록된 아이디입니다. \n");
        }
    }
    private static void processEditCommand(String[] args){
        if(args.length !=4){
            printHelp();
            return;
        }
        ChangePasswordService cPwdSvc = ctx.getBean("cPwdSvc", ChangePasswordService.class);
        try{
            cPwdSvc.changePassword(args[1], args[2], args[3]);
            System.out.println("암호를 변경했습니다.");
        }catch(DuplicateMemberException e){
            System.out.println("이미 존재하는 멤버입니다.");
        }catch(IdPasswordNotMatchingException e){
            System.out.println("아이디/비밀번호가 일치하지 않습니다.");
        }
    }

    private static void processListCommand(){
        MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
        listPrinter.printAll();
    }

    private static void processInfoCommand(String[] arg){
        if(arg.length !=2){
            printHelp();
            return;
        }
        MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
        infoPrinter.printMemberInfo(arg[1]);
    }
    private static void processVersionCommand(){
        VersionPrinter version = ctx.getBean("versionPrinter", VersionPrinter.class);
        version.print();
    }
    private static void printHelp(){
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인해주세요.");
        System.out.println("명령어 사용법");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재암호 변경암호");
        System.out.println("list");
        System.out.println();
    }
}
