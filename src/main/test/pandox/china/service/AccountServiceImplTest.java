package pandox.china.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_all_accounts() {
        ArrayList<MembInfo> membInfos = new ArrayList<>();
        MembInfo membInfo = new MembInfo();
        membInfo.setMembGuid(1);
        membInfo.setMembId("mmmkiller");
        membInfos.add(membInfo);
        when(accountRepository.findAll()).thenReturn(membInfos);

        List<AccountDTO> dtos = service.findAll();

        Assert.assertFalse(dtos.isEmpty());
        Assert.assertEquals("mmmkiller", dtos.get(0).getLogin());
    }

    @Test
    public void should_save_account_if_correct() {
        MembInfo membInfo = new MembInfo();
        membInfo.setMembId("mmmkiller");
        when(accountRepository.save(any(MembInfo.class))).thenReturn(membInfo);

        AccountDTO dto = new AccountDTO("mmmkiller", "uol123", "Matheus", "matheus@uol.com.br");

        AccountDTO save = service.save(dto);

        Assert.assertEquals(save.getLogin(), "mmmkiller");
    }


    @Test
    public void should_throw_error_if_login_already_exists() {
        MembInfo membInfo = new MembInfo();
        membInfo.setMembId("mmmkiller");
//        when(accountRepository.save(any(MembInfo.class))).thenReturn(membInfo);

        AccountDTO dto = new AccountDTO("mmmkiller", "uol123", "Matheus", "matheus@uol.com.br");

        AccountDTO save = service.save(dto);

        Assert.assertEquals(save.getLogin(), "mmmkiller");
    }
}