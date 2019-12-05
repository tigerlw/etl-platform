package com.dap.etl.platform.admin.inf.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dap.etl.platform.admin.inf.repo.mysql.dto.CustDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.services.elasticloadbalancingv2.ElasticLoadBalancingV2Client;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.Action;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.CreateRuleRequest;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.DescribeLoadBalancersRequest;
//import software.amazon.awssdk.services.elasticloadbalancingv2.model.DescribeLoadBalancersRequest.BuilderImpl;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.DescribeLoadBalancersResponse;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.DescribeRulesRequest;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.DescribeRulesResponse;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.ForwardActionConfig;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.HttpHeaderConditionConfig;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.LoadBalancer;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.Rule;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.RuleCondition;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.TargetGroupTuple;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

@RestController
public class AwsController {
	
	private final Logger logger = LogManager.getLogger(AwsController.class);
	
	@RequestMapping(value = "/getBuckers", method = RequestMethod.POST)
	public String getBuckers()
	{
		S3Client s3 = S3Client.builder().region(Region.US_EAST_1).build();

		// final AmazonS3 s3 =
		// AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
		ListBucketsResponse buckets = s3.listBuckets();
		
		System.out.println("Your Amazon S3 buckets are:");
		for (Bucket b : buckets.buckets()) {
			System.out.println("* " + b.name());
		}

		return "success";
	}
	
	
	@RequestMapping(value = "/getLb", method = RequestMethod.POST)
	public String loadBalance()
	{
		ElasticLoadBalancingV2Client client = ElasticLoadBalancingV2Client.builder().region(Region.AP_SOUTHEAST_1).build();
		
		//DescribeLoadBalancersRequest model
		//DescribeLoadBalancersRequest request = DescribeLoadBalancersRequest
		DescribeLoadBalancersResponse response = client.describeLoadBalancers();
		
		List<LoadBalancer> lbs =response.loadBalancers();
		
		for(LoadBalancer lb:lbs)
		{
			System.out.println(lb.dnsName());
			
			//lb.
		}
		
		
		DescribeRulesRequest rq = DescribeRulesRequest.builder()
				.ruleArns(
						"arn:aws:elasticloadbalancing:ap-southeast-1:911079401671:"
						+ "listener-rule/app/test/844df30102423a2f/07f7dfc78a5a152d/973e23684651a460")
				.build();
		
		
		
		DescribeRulesResponse rp = client.describeRules(rq);
		
		List<Rule> rules = rp.rules();
		
		for(Rule rule:rules)
		{
			System.out.println(rule.toString());
		}
		
		HttpHeaderConditionConfig httpHeaderConditionConfig = HttpHeaderConditionConfig.builder().
				httpHeaderName("ttt").values("123").build();
		
		RuleCondition rc = RuleCondition.builder().httpHeaderConfig(httpHeaderConditionConfig).field("http-header").build();
		
		
		TargetGroupTuple targetGroupTuple = TargetGroupTuple.builder().targetGroupArn("arn:aws:elasticloadbalancing:"
				+ "ap-southeast-1:911079401671:targetgroup/ttt/43a587deed5d41d5").weight(1).build();
		
		ForwardActionConfig forwardActionConfig = ForwardActionConfig.builder().targetGroups(targetGroupTuple).build();
		
		Action ac = Action.builder().forwardConfig(forwardActionConfig).type("forward").build();
		
		
		CreateRuleRequest createRuleRequest = CreateRuleRequest
				.builder()
				.listenerArn("arn:aws:elasticloadbalancing:ap-southeast-1:911079401671:"
						+ "listener/app/test/844df30102423a2f/07f7dfc78a5a152d")
				.conditions(rc).actions(ac).priority(2)
				.build();
				
				
		
		client.createRule(createRuleRequest);
		
		
		
		
		return "success";
	}
	

}
