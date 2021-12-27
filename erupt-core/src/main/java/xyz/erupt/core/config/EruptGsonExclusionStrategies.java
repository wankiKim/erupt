package xyz.erupt.core.config;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import lombok.SneakyThrows;
import xyz.erupt.annotation.config.EruptSmartSkipSerialize;
import xyz.erupt.core.service.EruptContextCtrl;
import xyz.erupt.core.service.EruptCoreService;
import xyz.erupt.core.util.ReflectUtil;
import xyz.erupt.core.view.EruptContext;
import xyz.erupt.core.view.EruptModel;

import java.lang.reflect.Field;

/**
 * @author YuePeng
 * date 2021/1/13 00:09
 */
public class EruptGsonExclusionStrategies implements ExclusionStrategy {

    @Override
    @SneakyThrows
    public boolean shouldSkipField(FieldAttributes f) {
        EruptContext eruptContext = EruptContextCtrl.get();
        if (null != eruptContext && null != f.getAnnotation(EruptSmartSkipSerialize.class)) {
            EruptModel erupt = EruptCoreService.getErupt(eruptContext.getName());
            Field ff = ReflectUtil.findClassField(erupt.getClazz(), f.getName());
            if (null == ff) return false;
            return !f.getDeclaringClass().getName().equals(ff.getDeclaringClass().getName());
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> incomingClass) {
        return false;
    }

}