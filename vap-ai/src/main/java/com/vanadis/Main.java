package com.vanadis;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import ai.djl.Application;
import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;

/**
 * Main
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/1/12 8:59 下午
 */
public class Main {

    public static void main(String[] args)
        throws MalformedModelException, ModelNotFoundException, IOException, TranslateException {
        // 读取一张图片
        String url = "https://github.com/awslabs/djl/raw/master/examples/src/test/resources/dog_bike_car.jpg";
        BufferedImage img = ImageIO.read(new URL(url));
        // 创建一个模型的寻找标准
        Criteria<BufferedImage, DetectedObjects> criteria =
            Criteria.builder()
                // 设置应用类型：目标检测
                .optApplication(Application.CV.OBJECT_DETECTION)
                // 确定输入输出类型 （使用默认的图片处理工具）
                .setTypes(BufferedImage.class, DetectedObjects.class)
                // 模型的过滤条件
                .optFilter("backbone", "resnet50")
                .optProgress(new ProgressBar())
                .build();

        // 创建一个模型对象
        try (ZooModel<BufferedImage, DetectedObjects> model = ModelZoo.loadModel(criteria)) {
            // 创建一个推理对象
            try (Predictor<BufferedImage, DetectedObjects> predictor = model.newPredictor()) {
                // 推理
                DetectedObjects detection = predictor.predict(img);
                System.out.println(detection);
            }
        }
    }
}
