package com.lld.splitwise.factory;

import com.lld.splitwise.model.SplitType;
import com.lld.splitwise.service.EqualSplitStrategy;
import com.lld.splitwise.service.ExactSplitStrategy;
import com.lld.splitwise.service.PercentSplitStrategy;
import com.lld.splitwise.service.SplitStrategy;
import org.springframework.stereotype.Component;

@Component
public class SplitStrategyFactory {
    public SplitStrategy getSplitStrategy(SplitType splitType) {
        switch (splitType) {
            case EQUAL: {
                return new EqualSplitStrategy();
            }
            case PERCENT: {
                return new PercentSplitStrategy();
            }
            case CUSTOM: {
                return new ExactSplitStrategy();
            }
        }
        return null;
    }
}
