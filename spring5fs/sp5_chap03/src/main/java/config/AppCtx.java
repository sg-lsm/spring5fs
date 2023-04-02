package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppCtx {
    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegSvc(){
        return new MemberRegisterService(memberDao());
    }
    @Bean
    public ChangePasswordService cPwdSvc(){
        ChangePasswordService cPwdSvc = new ChangePasswordService();
        cPwdSvc.setMemberDao(memberDao());
        return cPwdSvc;
    }
    @Bean
    public MemberPrinter printer(){
        return new MemberPrinter();
    }
    @Bean
    public MemberListPrinter listPrinter(){
        return new MemberListPrinter(memberDao(), printer());
    }
    @Bean
    public MemberInfoPrinter infoPrinter(){
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setMemberDao(memberDao());
        infoPrinter.setPrinter(printer());
        return infoPrinter;
    }
    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter version = new VersionPrinter();
        version.setMajorVersion(5);
        version.setMinorVersion(0);
        return version;
    }
}
