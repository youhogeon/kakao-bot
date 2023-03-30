package com.youhogeon.kakaogpt.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.youhogeon.kakaobot.config.Config;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
public class HelpServiceTest {

}
