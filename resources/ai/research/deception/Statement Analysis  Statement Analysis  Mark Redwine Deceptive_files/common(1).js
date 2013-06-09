/* Application base URL - Needs to change when application base is changed */

var domain_base_url = 'cruxlight.com';

var server_base_url = 'http://www.' + domain_base_url;
var firefox_extension_server_base_url = 'https://app-cruxlight.appspot.com';

/* Application Sub URL's - No Need to change unless servlet code is changed */

var map_cruxpad_urls = {
	'evernote_auth_req_url' : server_base_url + '/evernoteShare?action=getAccessToken&&pluginId=',
	'pocket_auth_req_url' : server_base_url + '/getPocketShare',
	'summary_req_url' : server_base_url + '/cruxpad',
	'view_summary_req_url' : server_base_url + '/cruxpadView',
	'view_summary_share_link' : server_base_url + '/html/view-summary.html',
	'view_summary_msg_link' : server_base_url + '/html/pages/view-summary-msg.html',
	'analytics_url' : server_base_url + '/cruxpadAnalytics',
	'safari_download_extension_url' : server_base_url + '/extensions/safari/cruxlight.safariextz',
	'firefox_download_extension_url' : firefox_extension_server_base_url + '/extensions/firefox/cruxlight.xpi',
	'safari_installation_guide_url' : server_base_url + '/html/pages/safari-installation-guide.html',
	'firefox_installation_guide_url' : server_base_url + '/html/pages/firefox-installation-guide.html',
	'cruxlight_help_url' : server_base_url + '#help',
	'getpocket_share_url' : server_base_url + '/html/pages/getpocketauth.html',
	'evernote_wait_url' : server_base_url + '/html/pages/evernotewait.html',
	'feedback_req_url' : server_base_url + '/cruxpadFeedback',
	'total_time_saved_req_url': server_base_url + '/totalTimeSaved'
}

var debug_flag = false;

var recaptcha_public_key = "6Ld8Ad0SAAAAAFiew5xcdBewOsEPUSXnBHrkOFca";
var feedback_message_min_length = 20;
var feedback_message_max_length = 1000;

var map_cruxpad_iframe_extensions_sent_msgs = {
	'cruxpad_footer_close_msg' : 'cruxpad_footer_close_msg',
	'cruxpad_dialog_close_msg' : 'cruxpad_dialog_close_msg',
	'cruxpad_footer_select_keyword' : 'cruxpad_footer_select_keyword_',
	'cruxpad_footer_cruxpad_view_msg' : 'cruxpad_footer_cruxpad_view_msg',
	'cruxpad_show_iframe_msg' : 'cruxpad_show_iframe_msg',
	'cruxpad_summary_error_msg' : 'cruxpad_summary_error_msg',
	'cruxpad_slider_change_msg' : 'cruxpad_slider_change_msg',
	'cruxpad_change_highlight_color_msg' : 'cruxpad_change_highlight_color_msg',
	'cruxpad_change_footer_size_msg' : 'cruxpad_change_footer_size_msg',
	'cruxpad_choose_highlight_color_msg' : 'cruxpad_choose_highlight_color_msg',
	'cruxpad_change_footer_size_normal_msg' : 'cruxpad_change_footer_size_normal_msg', //New message event added by Vikas on 20th Dec'12 to handle the change in wrapper size when search text error message is faded out.
	'cruxpad_notify_frame_ready_msg' : 'cruxpad_notify_frame_ready',
	'cruxpad_close_guideline_frame_msg' : 'cruxpad_close_guideline_frame'
}

var map_cruxpad_extensions_iframe_event_names = {
	'cruxpad_frame' : 'cruxpad_frame'
}

var map_cruxpad_extensions_iframe_received_msgs = {
	'canvas_clicked' : 'canvas_clicked',
	'view_summary_clicked' : 'view_summary_clicked',
	'close_footer_dialog_keyboard' : 'close_footer_dialog_keyboard',
	'show_dialog_popup_disable' : 'show_dialog_popup_disable',
	'total_time_saved_calculated' : 'total_time_saved_calculated'
}

var cruxpad_dialog_notification_msg = {
	'evernote_connect_msg' : 'Please wait while we contact Evernote server ...',
	'technical_error_msg' : 'There is some technical error in the system. Please try after sometime.',
	'cruxpad_feedback_success_msg' : 'Thank you for your feedback !',
	'cruxpad_feedback_already_given_msg' : 'You have already given the feedback !',
	'cruxpad_feedback_input_name_invalid' : 'Invalid Name Entered',
	'cruxpad_feedback_input_email_invalid' : 'Invalid Email Entered',
	'cruxpad_feedback_input_email_blank' : 'Email cannot be blank',
	'cruxpad_feedback_input_msg_blank' : 'Message to send cannot be blank.',
	'cruxpad_feedback_input_msg_minlength' : 'Message should be atleast of ' + feedback_message_min_length + ' characters.',
	'cruxpad_feedback_input_msg_maxlength' : 'Message should not be greater than ' + feedback_message_max_length + ' characters.',
	'cruxpad_feedback_input_captcha_blank' : 'Please enter the captcha value',
}

var is_feedback_given = false;

/* Params for the functions used to seperate get the URL params based on the added string */

/* Commented by Vikas for a common URL param fetch parameter function - started */

/*
 var url_param_identifier="?url=";
 var plugin_id_param_identifier="&&pluginId=";
 var social_media_source_param_identifier="&&source=";

 var article_title_param_identifier="?articleTitle=";
 var article_link_param_identifier = "&&articleLink=";
 */

/* Commented by Vikas for a common URL param fetch parameter function - ended */

function get_screen_height() {
	var screen_height = 0;
	if ( typeof (window.innerHeight) == 'number') {
		//Non-IE
		screen_height = window.innerHeight;
	} else if (document.documentElement && document.documentElement.clientHeight) {
		//IE 6+ in 'standards compliant mode'
		screen_height = document.documentElement.clientHeight;
	} else if (document.body && document.body.clientHeight) {
		//IE 4 compatible
		screen_height = document.body.clientHeight;
	}
	return screen_height;
}

function get_screen_width() {
	var screen_width = 0;
	if ( typeof (window.innerWidth) == 'number') {
		//Non-IE
		screen_width = window.innerWidth;
	} else if (document.documentElement && document.documentElement.clientWidth) {
		//IE 6+ in 'standards compliant mode'
		screen_width = document.documentElement.clientWidth;
	} else if (document.body && document.body.clientWidth) {
		//IE 4 compatible
		screen_width = document.body.clientWidth;
	}
	return screen_width;
}

function log_console_msg(msg) {
	debug_flag && console.log(msg);
}

function get_url_params(first_seperator, second_seperator) {
	var page_url = $.trim(window.location.href);
	first_seperator = $.trim(first_seperator);
	second_seperator = $.trim(second_seperator);
	var url_param = page_url.substring(page_url.indexOf(first_seperator) + first_seperator.length);
	if (second_seperator != '') {
		url_param = url_param.substring(0, url_param.lastIndexOf(second_seperator));
	}
	return url_param;
}

function escape_for_html(inp_string) {
	var replace_char = {
		"&" : "amp",
		'"' : "quot",
		"<" : "lt",
		">" : "gt"
	};
	return inp_string.replace(/[&"<>]/g, function(match_char) {
		return ("&" + replace_char[match_char] + ";");
	});
}

/*This function returns the string between first index of "?url=" and last index of "&&pluginId=" in the page url */

/* Commented by Vikas for a common URL param fetch parameter function - stared */

/*
 function getUrl(page_source){
 var page_url=window.location.href;
 var url=page_url.substring(page_url.indexOf(url_param_identifier) +url_param_identifier.length);
 if(page_source == 'S'){ // For normal summary request
 url=url.substring(0,url.lastIndexOf(plugin_id_param_identifier));
 } else if(page_source == 'VS') { // For view summary request
 url=url.substring(0,url.lastIndexOf(social_media_source_param_identifier));
 }
 return url;
 }
 */

/* Commented by Vikas for a common URL param fetch parameter function - ended */

/*This function returns the string between first index of "?articleTitle=" and last index of "&&articleLink=" in the page url */

/* Commented by Viral for a fetch article title - stared */
/*
 function getArticleTitle(){
 var page_url=window.location.href;
 var article_title=page_url.substring(page_url.indexOf(article_title_param_identifier) +article_title_param_identifier.length);
 article_title=article_title.substring(0,article_title.lastIndexOf(article_link_param_identifier));
 return article_title;
 }
 */
/* Commented by Viral for a fetch article title - ended */

/*This function returns the string after the last index of "&&articleLink=" in the page url */
/* Commented by Viral for a fetch article link - stared */
/*
 function getArticleLink(){
 var page_url=window.location.href;
 var article_link=page_url.substring(page_url.lastIndexOf(article_link_param_identifier)+article_link_param_identifier.length);
 return article_link;
 }
 */
/* Commented by Viral for a fetch article title - ended */
/*This function returns the string after the last index of "&&pluginId=" in the page url */
/* Commented by Viral for a fetch pluginid - stared */
/*
 function getPluginId(){
 var page_url=window.location.href;
 var plugin_id=page_url.substring(page_url.lastIndexOf(plugin_id_param_identifier)+plugin_id_param_identifier.length);
 return plugin_id;
 }
 */
/* Commented by Viral for a fetch article title - ended */

/*This function returns the string after the last index of "&&src=" in the page url */
/*  Commented by Viral for a fetch socialmedia source - stared */
/*
 function getSocialMediaSource(){
 var page_url=window.location.href;
 var sm_source=page_url.substring(page_url.lastIndexOf(social_media_source_param_identifier)+social_media_source_param_identifier.length);
 return sm_source;
 }
 */
/* Commented by Viral for a fetch article title - stared */