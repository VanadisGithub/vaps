import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.Config;

/**
 * K8s
 *
 * @author yy287502@alibaba-inc.com
 * @date 2024/4/11 11:10
 */
public class K8s {

    public static void main(String[] args) {
        try {
            // 加载Kubernetes配置文件（通常是 ~/.kube/config）
            ApiClient client = Config.defaultClient();

            // 设置配置
            Configuration.setDefaultApiClient(client);

            // 创建API的实例
            CoreV1Api api = new CoreV1Api();

            // 获取所有的Pods
//            V1PodList list = api.listPodForAllNamespaces().execute();
            V1PodList list = api.listNamespacedPod("env15").execute();

            // 遍历并打印Pod名称
            list.getItems().forEach((pod) ->
                    System.out.println(pod.getMetadata().getName())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
