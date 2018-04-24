package com.wwz.stat.Service;

import com.wwz.stat.Model.StatResult;
import org.springframework.stereotype.Service;
import java.io.File;
/**
 * Created by alice on 18/4/24.
 */
@Service
public interface BeihaiStatService {

    public StatResult beihaiStat();
}
