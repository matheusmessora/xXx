package br.com.pandox.xxx.service;

import org.springframework.stereotype.Service;

@Service
public class AsyncService {

//    private static Logger log = Logger.getLogger(AsyncService.class);
//
//    @Autowired
//    private MembStatRepository membStatRepository;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private AuthenticationService authService;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private CacheService cache;
//
//    @Scheduled(cron="0 0 3 * * *")
//    public void processVIP() {
//        log.info("PROCESSANDO VIP");
//        List<MembInfo> membInfos = accountRepository.findByScfIsVip((short) 1);
//        log.info("VIP. qtdVIPs=" + membInfos.size());
//        for (MembInfo membInfo : membInfos) {
//            membInfo.decreaseVIP();
//
//            if(membInfo.getScfVipDays() <= 0){
//                log.info("VIP.REMOVIDO. login=" + membInfo.getMembId() + ", credits=" + membInfo.getScfVipMoney());
//                membInfo.setScfIsVip((short) 0);
//                membInfo.setScfVipDays((short) 0);
//            }
//        }
//        log.info("VIP.SUCESSO");
//        accountRepository.save(membInfos);
//    }
//
//
//    @Scheduled(fixedRate=3600000)
//    public void processOnlineHour() {
//        log.info("PROCESSANDO ONLINE HOUR");
//        List<MembStat> membStats = membStatRepository.findByConnectStat(1);
//        log.info("totalOnline="+membStats.size());
//        for (MembStat membStat : membStats) {
//            membStat.addHour();
//        }
//        membStatRepository.save(membStats);
//
//    }
}
