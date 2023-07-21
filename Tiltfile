SOURCE_IMAGE = 'gcr.io/api-gw-on-gcp/build-service/spring-kafka-producer'
LOCAL_PATH = '.'
NAMESPACE = os.getenv("NAMESPACE", default='default')
k8s_custom_deploy(
    'spring-kafka-producer',
    apply_cmd="tanzu apps workload apply -f config/workload.yaml --live-update" +
              " --local-path " + LOCAL_PATH +
              " --source-image " + SOURCE_IMAGE +
              " --namespace " + NAMESPACE +
              " --yes " +
              " && kubectl get workload spring-kafka-producer --namespace " + NAMESPACE + " -o yaml",
    delete_cmd="tanzu apps workload delete -f config/workload.yaml --namespace " + NAMESPACE + " --yes",
    deps=['pom.xml', './target/classes'],
    container_selector='workload',
    live_update=[
      sync('./target/classes', '/workspace/BOOT-INF/classes')
    ]
)

allow_k8s_contexts('gke_api-gw-on-gcp_us-central1_tap-151')
k8s_resource('spring-kafka-producer', port_forwards=["8080:8080"],
            extra_pod_selectors=[{'serving.knative.dev/service': 'spring-kafka-producer'}])

