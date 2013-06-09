/** ******************************** ALL VARAIBLES ******************* */
{
	var twenty_per_summary = "";
	var sent_d_a20 = [];
	var twenty_percent_sent_no;

	var forty_per_summary = "";
	var sent_d_a40 = [];
	var forty_percent_sent_no;

	var short_summary = "";
	var short_summary_share = "";

	var sent_d_aoriginal = [];
	var original = "";
	var original_para_parts = [];
	var long_summary_keywords = "";
	var current_content = [];

	// variables that store values from json response from server
	var json_response = "";
	var article_title = "";
	var summary_status = "";
	var sentences_cnt = "";
	var keyphrases_cnt = "";
	var keyentities_cnt = "";
	var keyphrases = "";
	var keyentities = "";
	var domain_name = "";
	var all_sentences = "";
	var search_words;

	var disable_popup_flag = 'N';

	var twitter_keywords = "";
	var selected_keyword = "";
	// variables for storing querystring parameters
	var plugin_id = "";
	var url = "";
	var view_source = "";

	// Cruxpad Analytics code varaible

	var crux_dialog_active_time = 0;
	// 1
	var crux_dialog_active_cnt = 0;
	// 2
	var crux_footer_to_dialog_open_cnt = 0;
	// 3
	var crux_rightclick_to_dialog_open_cnt = 0;
	// 4
	var crux_footer_active_time = 0;
	// 5
	var crux_button_to_dialog_cnt = 0;
	// 6
	var crux_footer_active_timeout_var;
	var crux_dialog_active_timeout_var;
	var facebook_shared_cnt = 0;
	// 7
	var twitter_shared_cnt = 0;
	// 8
	var googleplus_shared_cnt = 0;
	// 9
	var facebook_like_cnt = 0;
	// 10
	var googleplus_like_cnt = 0;
	// 11
	var is_reported_bad_summary = "N";
	// 12
	var save_pocket_cnt = 0;
	// 13
	var save_evernote_cnt = 0;
	// 14
	var select_all_cnt = 0;
	// 15
	var footer_active_total_cnt = 0;
	//16
	var crux_focuswords_to_dialog_open_cnt = 0;
	//17
	var crux_searchword_to_dialog_open_cnt = 0;
	// 18
	var footer_searchword_input_used_cnt = 0;
	// 19
	var footer_colorpicker_color_change_cnt = 0;
	//20
	var footer_slider_change_cnt = 0;
	//21
	var print_summary_cnt = 0;
	//22

	// Added by Vikas for changes in the footer & dialog feedback - starts

	var footer_facebook_share_cnt = 0;
	// 23
	var footer_twitter_share_cnt = 0;
	//24
	var feedback_click_cnt = 0;
	//25
	var feedback_send_cnt = 0;
	//26

	// Added by Vikas for changes in the footer & dialog feedback - ends

	var cruxpad_analytics = new function() {
	this.analytics = [];
	}

	// variables for time saved calculation
	var total_words = 0;
	var summary_words = 0;
	var avg_words_per_minute = 200;
	var time_saved_in_minutes;
	var total_time_saved_in_minutes;
	// all the flags are defined here
	var scrollbar_init_flag = false;
	var debug = true;

	var map_mini_footer_msgs = {
		'cruxlight_logo_hover_msg' : 'CruxLight automatically distills the web article to its essence thus helping the user get the gist of the article.',
		'close_hover_msg' : 'Close',
		'focus_words_hover_msg' : 'Click on the keywords to change the point of view of summary.',
		'cruxview_button_hover_msg' : 'Click here to view summary.',
		'slider_hover_msg' : 'Use the slider to change the percentage of sentences highlighted in the article. Min is 0% and Max is 90%. ',
		'color_picker_hover_msg' : 'Change the highlighting color by picking a color from the palette.',
		'search_text_blank_msg' : 'The text to search cannot be blank. Please enter a proper search text.',
		'search_text_invalid_msg' : 'The text you are trying to search does not exists in the article.',
		'facebook_share_hover_msg' : 'Share article summary on Facebook',
		'twitter_share_hover_msg' : 'Share article summary on Twitter',
	}

	var map_mini_footer_slider_constants = {
		'slider_init_marker_value' : 30, // Default value that needs to be put. 30% for short summary. Also the variable will contain the selected value of the slider.
		'slider_max_marker_value' : 90, // Our slider will range from 10 to 90 i.e. 10% to 90%
		'slider_min_marker_value' : 0, // Our slider will range from 10 to 90 i.e. 10% to 90%
		'slider_event_fire_on_previous_current_diff' : 1, // If the difference previous and current value if more than this value , fire highlight event
		'slider_highlight_default_start_index' : 0, // Default start percentage from which highlighting will start
		'slider_event_fire_prev_curr_sent_diff' : 1 // If the difference in sentences is more than the defined value, fire highlight event.
	}

	var slider_previous_value = map_mini_footer_slider_constants['slider_init_marker_value'];
	var slider_current_value;

	var map_mini_footer_other_constants = {
		'msg_timeout_interval_value' : 5000, // Default timeout interval in milliseconds
		'msg_fadeout_interval_value' : 3000, // Default fadeout interval in milliseconds
		'search_delay_autosuggest' : 40, // Default delay interval in milliseconds for autosuggest
		'keyevent_code_to_search' : 13, // Search should be done when enter is pressed. Keyevent code for enter is 13.
		'color_picker_transparent_color_code' : 'transparent' // Color code that will be used to show transparency effect when slider value is decreased.
	}

	var map_other_constants = {
		'msg_seperator_special_char' : '::', // If the message that needs to be passed to the extension contains a multiple values, this seperator will be used to join the value and the same seperator will be used to seperate in extension
	}

	var color_picker_colors_arr = new Array("FFFF99", "FF9000", "B1D60A", "444EEE", "E8E1F7", "FFE7DA");

	var map_cruxpad_dialog_constants = {
		'short_summary_percentage' : 20,
		'short_summary_minimum_sentences' : 7,
		'medium_summary_percentage' : 40,
		'medium_summary_minimum_sentences' : 13,
		'msgs_timeout_interval_milliseconds' : 5000,
		'msgs_fadeout_interval_milliseconds' : 3000,
		'avg_words_per_minute_time_save_calc' : 200,
		'print_timeout_milliseconds' : 200
	}

	// Constants related to themes

	var map_cruxpad_themes_code = {
		'default_theme' : 'default',
		'theme1_theme' : 'theme1'
	}

	var dialog_display_theme = map_cruxpad_themes_code['default_theme'];
	var dialog_left_position = 0;

	var map_notify_msg_position = {
		'evernote_left' : '150',
		'evernote_bottom' : '40',
		'feedback_right' : '0',
		'feedback_bottom' : '40',
		'evernote_theme1_decrement_value' : '50'
	}

	var recaptcha_load_flag = false;
}

//added for google analytics
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-37432804-1']);
_gaq.push(['_trackPageview']);

(function() {
	var ga = document.createElement('script');
	ga.type = 'text/javascript';
	ga.async = true;
	ga.src = 'https://ssl.google-analytics.com/ga.js';
	var s = document.getElementsByTagName('script')[0];
	s.parentNode.insertBefore(ga, s);
})();

(function() {
	var po = document.createElement('script');
	po.type = 'text/javascript';
	po.async = true;
	po.src = 'https://apis.google.com/js/plusone.js';
	var s = document.getElementsByTagName('script')[0];
	s.parentNode.insertBefore(po, s);
})();

/** ******************************* UTILITY FUNCTIONS START***************** */

function sort_asc_order(a, b) {
	return parseInt(a.id) - parseInt(b.id);
}

function get_time_saved_in_seconds() {
	// time saved calculation
	time_saved_in_seconds = (total_words - summary_words) * 60 / avg_words_per_minute;
	return Math.floor(time_saved_in_seconds);
}

function notify_bottom_left(msg, bottom, left) {
	$("#cruxpad_notify_msg").css({
		"bottom" : bottom + 'px',
		"left" : left + 'px',
		"right" : ''
	});

	$("#cruxpad_notify_msg").html(msg);
	$("#cruxpad_notify_msg").show();

	setTimeout(function() {
		$("#cruxpad_notify_msg").fadeOut(map_cruxpad_dialog_constants['msgs_fadeout_interval_milliseconds']);
	}, map_cruxpad_dialog_constants['msgs_timeout_interval_milliseconds']);
}

function notify_bottom_right(msg, bottom, right) {
	$("#cruxpad_notify_msg").css({
		"bottom" : bottom + 'px',
		"right" : right + 'px',
		"left" : ''
	});
	$("#cruxpad_notify_msg").html(msg);
	$("#cruxpad_notify_msg").show();

	setTimeout(function() {
		$("#cruxpad_notify_msg").fadeOut(map_cruxpad_dialog_constants['msgs_fadeout_interval_milliseconds']);
	}, map_cruxpad_dialog_constants['msgs_timeout_interval_milliseconds']);
}

/********************************* UTILITY FUNCTIONS END ***************** */

/********************************* FOOTER FUNCTIONS START***************** */

/*
 * This function sets the width of the content div dynamically
 */

function footer_main() {

	// Function to set the width of the content based on the screen resolution
	set_footer_content_width();

	// Function to initialize color picker for the footer
	init_color_picker();

	// Function to set the width of the area where focus words will be displayed.
	set_focus_words_area();

	// Function to initialize slider for footer with max and min values as specified in the constants
	init_slider(map_mini_footer_slider_constants['slider_init_marker_value']);

	// Function to define auto suggest for the footer search input tag
	turn_auto_suggest();

	// Function to assign title attribute to each of the element on which message needs to be shown on hover
	assign_title_msgs_footer();

	// Function to assign footer anonymous events such as when color picker changed, entered pressed in search input, clicked and focus out of search input.
	assign_footer_events();

	// Function to assign css attribute to footer elements
	assign_footer_attributes();
}

function assign_footer_attributes() {
	$("#cruxpad_mini_footer_logo_anchor").attr("href", server_base_url);
}

function set_footer_content_width() {
	log_console_msg('summary.html :: function set_footer_content_width : starts');
	var footer_content_div_width = get_screen_width() - $('#cruxpad_mini_footer_logo_div').width() - $('#cruxpad_mini_footer_close_div').width();
	$("#cruxpad_mini_footer_content").css({
		"width" : footer_content_div_width + "px"
	});
	log_console_msg('summary.html :: function set_footer_content_width :  ends');
}

function assign_footer_events() {

	$('#cruxpad_mini_footer_close').live('click', function() {
		send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_footer_close_msg']);
	});

	$('#cruxpad_mini_footer_cruxpad_view').live('click', function() {
		send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_footer_cruxpad_view_msg']);
	});

	// Code to execute when color has been changed - Need to execute color change steps when done

	$('.colorPicker-picker').live('click', function() {
		send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_choose_highlight_color_msg']);
	});

	$('#color_picker').change(function() {
		handle_color_picker_color_change();
	});

	// Code to change the value of input tag when clicked

	$("#cruxpad_search_words").live("click", function() {
		$(this).val("");
	});

	// Code will be called when enter is pressed in the search box to search the entered word.

	$("#cruxpad_search_words").keydown(function(event) {
		if (event.keyCode == map_mini_footer_other_constants['keyevent_code_to_search'] && $("#cruxpad_mini_footer").is(':visible')) {
			log_console_msg($('#cruxpad_search_words').val());
			var searched_word = jQuery.trim($('#cruxpad_search_words').val()).replace(/\s{2,}/g, ' ');
			$("#cruxpad_search_words").val("");
			process_search_word(searched_word);

		}
	});

	// Code to call when focus is out from the search input field

	$("#cruxpad_search_words").focusout(function() {
		$("#cruxpad_search_words").val("Search");
	});

	// Added by Vikas for new features in the footer - starts

	$("#cruxpad_mini_footer_twitter_share_img").live("click", function() {
		twitter_share();
	});

	$("#cruxpad_mini_footer_facebook_share_img").live("click", function() {
		fb_share();
	});

	// Added by Vikas for new features in the footer - ENDS

}

function handle_body_background_themes() {
	if (dialog_display_theme === map_cruxpad_themes_code['theme1_theme']) {
		if ($("#cruxpad_dialog").is(':visible')) {
			$("#cruxpad_body").addClass("cruxpad_body_css_theme1");
		} else if ($("#cruxpad_mini_footer").is(':visible') && disable_popup_flag == 'N') {
			$("#cruxpad_body").removeClass("cruxpad_body_css_theme1");
		}
	}
}

function assign_dialog_events() {
	$("#cruxpad_dialog_link").live("click", function() {
		handle_dialog_footer_transition();
	});

	$("#cruxpad_dialog_close").live("click", function() {
		handle_dialog_footer_transition();
	});

	$("#cruxpad_feedback_send_button").live("click", function() {
		handle_feedback_send();
	});
}

function handle_feedback_send() {
	if (validate_feedback_input_values()) {
		feedback_send_cnt++;
		send_feedback();
	}
}

function send_feedback() {

	var input_name = jQuery.trim($("#cruxpad_feedback_name").val());
	var input_email = jQuery.trim($("#cruxpad_feedback_email").val());
	var input_message = jQuery.trim($("#cruxpad_feedback_msg").val());
	var recaptcha_response_field = jQuery.trim($("#recaptcha_response_field").val());
	var recaptcha_challenge_field = jQuery.trim($("#recaptcha_challenge_field").val());

	$.ajax({
		url : map_cruxpad_urls['feedback_req_url'],
		type : "POST",
		cache : true,
		data : "pluginId=" + plugin_id + "&&recaptchaChallenge=" + recaptcha_challenge_field + "&&recaptchaResponse=" + recaptcha_response_field + "&&name=" + input_name + "&&email=" + input_email + "&&message=" + input_message,
		error : function(jqXHR, textStatus, errorThrown) {
			log_console_msg(errorThrown);
		},
		success : function(data) {
			log_console_msg('   GOT Json Data from server');
			alert('Process Flag: ' + data.processFlag);
			alert('Message: ' + data.msg);
			if (data.processFlag == 'N') {
				Recaptcha.reload();
			}
		}
	});
}

function validate_feedback_input_values() {

	var input_name = jQuery.trim($("#cruxpad_feedback_name").val());
	var input_email = jQuery.trim($("#cruxpad_feedback_email").val());
	var input_message = jQuery.trim($("#cruxpad_feedback_msg").val());
	var input_captcha_code = jQuery.trim($("#recaptcha_response_field").val());

	var validation_success_flag = true;

	var name_regex = new RegExp(/^[A-Za-z\s'.]+$/gi);
	//var email_regex = new RegExp(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/);
	var email_regex = new RegExp(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);

	log_console_msg('Name: ' + input_name + '>>Email: ' + input_email + '>>Message: ' + input_message);

	if (input_name && input_name != '') {
		if (!name_regex.test(input_name)) {
			validation_success_flag = false;
			$("#cruxpad_feedback_notify_name").html(cruxpad_dialog_notification_msg['cruxpad_feedback_input_name_invalid']);
		}
	}

	if (input_email && input_email != '') {
		if (!email_regex.test(input_email)) {
			validation_success_flag = false;
			$("#cruxpad_feedback_notify_email").html(cruxpad_dialog_notification_msg['cruxpad_feedback_input_email_invalid']);
		}
	} else {
		validation_success_flag = false;
		$("#cruxpad_feedback_notify_email").html(cruxpad_dialog_notification_msg['cruxpad_feedback_input_email_blank']);
	}

	if (input_message && input_message != '') {
		if (input_message.length < feedback_message_min_length) {
			validation_success_flag = false;
			$("#cruxpad_feedback_notify_msg").html(cruxpad_dialog_notification_msg['cruxpad_feedback_input_msg_minlength']);
		} else if (input_message.length > feedback_message_max_length) {
			validation_success_flag = false;
			$("#cruxpad_feedback_notify_msg").html(cruxpad_dialog_notification_msg['cruxpad_feedback_input_msg_maxlength']);
		}
	} else {
		validation_success_flag = false;
		$("#cruxpad_feedback_notify_msg").html(cruxpad_dialog_notification_msg['cruxpad_feedback_input_msg_blank']);
	}

	if (!input_captcha_code || input_captcha_code == '') {
		validation_success_flag = false;
		$("#cruxpad_feedback_notify_captcha").html(cruxpad_dialog_notification_msg['cruxpad_feedback_input_captcha_blank']);
	}

	return validation_success_flag;
}

function handle_dialog_footer_transition() {
	send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_dialog_close_msg']);
	$('#google_plus_div').hide();
	$('#fb_like_div').hide();
	$("#cruxpad_dialog").hide();
	$("#cruxpad_mini_footer").show();
	$("#cruxpad_mini_footer_msg_palette_div").show();
	handle_body_background_themes();
	increment_footer_active_cnt_analytics();
}

/*
 * Added by Vikas on 20 Dec'12 to make sure that when color picker is hidden, we change
 * the size of the wrapper to normal.
 * The function will be called from the color-picker library.
 * */

function color_picker_hide() {
	change_footer_height_normal();
}

//Code to set the width of the content div dynamically

//Run the code when document ready for color picker initialization

function init_color_picker() {
	$('#color_picker').colorPicker({
		pickerDefault : color_picker_colors_arr[0], // Default value passed will always be the first color specified in the array
		colors : color_picker_colors_arr,
		transparency : true
	});
}

function handle_color_picker_color_change() {

	footer_colorpicker_color_change_cnt++;
	var highlight_start_index = Math.floor((map_mini_footer_slider_constants['slider_highlight_default_start_index'] * json_response.sentencesCnt) / 100);
	var highlight_end_index = Math.floor((slider_previous_value * json_response.sentencesCnt) / 100);

	if (Math.abs(highlight_end_index - highlight_start_index) > 0) {
		var msg = map_cruxpad_iframe_extensions_sent_msgs['cruxpad_change_highlight_color_msg'] + "-" + highlight_start_index + map_other_constants['msg_seperator_special_char'] + highlight_end_index + map_other_constants['msg_seperator_special_char'] + $("#color_picker").val();
		send_msg_to_content_script(msg);
	}
	log_console_msg("Color Changed: " + $("#color_picker").val());
}

//Code to set the width of the focus words div dynamically

function set_focus_words_area() {

	var curr_focus_area_width = 0;
	var max_focus_area_width = 0;

	var focus_li_cnt = 0;

	var focus_words_html_str = '<ul class=focus_words_ul id=focus_words_ul>';

	$.each(keyentities, function(index, key) {
		if (key != null) {
			focus_li_cnt++;
			focus_words_html_str += '<li id=focus_li_' + focus_li_cnt + '>' + key.word + '</li>';
		}

	});
	$.each(keyphrases, function(index, key) {
		if (key != null) {
			focus_li_cnt++;
			focus_words_html_str += '<li id=focus_li_' + focus_li_cnt + '>' + key.word + '</li>';
		}

	});

	focus_words_html_str += '</ul>';

	$("#cruxpad_mini_footer_focus_words").html(focus_words_html_str);

	if (focus_li_cnt > 0) {
		$("#focus_li_1").css({
			"margin-left" : "15px"
		});
	}

	for (var count = 1; count <= focus_li_cnt; count++) {
		$('#focus_li_' + count).attr('title', map_mini_footer_msgs['focus_words_hover_msg']);

		// Assigning on click event for each of the li element
		$('#focus_li_' + count).live('click', function() {
			handle_footer_focus_words_selection($(this).html());
		});
	}

	reset_focus_words_width(focus_li_cnt);

}

function handle_footer_focus_words_selection(clicked_focus_word) {
	crux_focuswords_to_dialog_open_cnt++;
	send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_footer_select_keyword'] + clicked_focus_word);
}

function reset_focus_words_width(focus_li_cnt) {

	log_console_msg('Screen Width: ' + get_screen_width());
	log_console_msg('Cruxpad View: ' + $('#cruxpad_mini_footer_cruxpad_view').outerWidth(true));
	log_console_msg('Color Picker: ' + $('#cruxpad_mini_footer_color_picker').outerWidth(true));
	log_console_msg('Search: ' + $('#cruxpad_mini_footer_search').outerWidth(true));
	log_console_msg('Logo: ' + $('#cruxpad_mini_footer_logo_div').outerWidth(true));
	log_console_msg('Close: ' + $('#cruxpad_mini_footer_close_div').outerWidth(true));
	log_console_msg('Max Text: ' + $('#cruxpad_mini_footer_slider_max_text').outerWidth(true));
	log_console_msg('Min Text: ' + $('#cruxpad_mini_footer_slider_min_text').outerWidth(true));
	log_console_msg('Slider: ' + $('#cruxpad_mini_footer_slider').outerWidth(true));
	log_console_msg('Facebook Like: ' + $('#cruxpad_mini_footer_facebook_like').outerWidth(true));
	log_console_msg('Facebook Share: ' + $('#cruxpad_mini_footer_facebook_share').outerWidth(true));
	log_console_msg('Twiter Share: ' + $('#cruxpad_mini_footer_twitter_share').outerWidth(true));

	max_focus_area_width = get_screen_width() - $('#cruxpad_mini_footer_cruxpad_view').outerWidth(true) - $('#cruxpad_mini_footer_logo_div').outerWidth(true) - $('#cruxpad_mini_footer_close_div').outerWidth(true) - $('#cruxpad_mini_footer_color_picker').outerWidth(true) - $('#cruxpad_mini_footer_search').outerWidth(true) - $('#cruxpad_mini_footer_slider_max_text').outerWidth(true) - $('#cruxpad_mini_footer_slider_min_text').outerWidth(true) - $('#cruxpad_mini_footer_slider').outerWidth(true) - $('#cruxpad_mini_footer_facebook_like').outerWidth(true) - $('#cruxpad_mini_footer_twitter_share').outerWidth(true) - $('#cruxpad_mini_footer_facebook_share').outerWidth(true);

	log_console_msg("Max width: " + max_focus_area_width);

	curr_focus_area_width = $("#cruxpad_mini_footer_focus_words").outerWidth(true);

	log_console_msg('Current Width: ' + curr_focus_area_width);

	while (curr_focus_area_width > max_focus_area_width) {
		$('#focus_li_' + focus_li_cnt).remove();
		focus_li_cnt--;
		curr_focus_area_width = $("#cruxpad_mini_footer_focus_words").outerWidth(true);
	}

	$("#cruxpad_mini_footer_search").css({
		"margin-left" : $("#cruxpad_mini_footer_focus_words").outerWidth(true)
	});

	log_console_msg('Focus Words Div: ' + $("#cruxpad_mini_footer_focus_words").outerWidth(true));
	log_console_msg('Search Padding: ' + $("#cruxpad_mini_footer_search").css('margin-left'));
}

function init_slider(current_slider_value) {

	$("#cruxpad_mini_footer_slider").slider({
		range : "min",
		value : current_slider_value,
		min : map_mini_footer_slider_constants['slider_min_marker_value'],
		max : map_mini_footer_slider_constants['slider_max_marker_value'],
		slide : function(event, ui) {
			document.getElementById("cruxpad_mini_footer_slider_value").value = ui.value;
			highlight_summary_sentences();
		}
	});
}

function highlight_summary_sentences() {
	slider_current_value = parseInt(document.getElementById("cruxpad_mini_footer_slider_value").value);
	slider_previous_value = parseInt(slider_previous_value);
	log_console_msg('Current Slider Value: ' + slider_current_value);
	log_console_msg('Current Previous Value: ' + slider_previous_value);
	if (slider_current_value > slider_previous_value) {
		log_console_msg('if called');
		handle_highlight_summary_call(slider_previous_value, slider_current_value, $("#color_picker").val(), false);
	} else if (slider_current_value < slider_previous_value) {
		log_console_msg('else called');
		handle_highlight_summary_call(slider_current_value, slider_previous_value, map_mini_footer_other_constants['color_picker_transparent_color_code'], false);
	}
	/*var difference = (slider_current_value > slider_previous_value) ? (slider_current_value - slider_previous_value) : (slider_previous_value - slider_current_value);
	 if (difference >= map_mini_footer_slider_constants['slider_event_fire_on_previous_current_diff']) {
	 log_console_msg('difference:- ' + difference);
	 send_msg_to_content_script(cruxpad_slider_change_msg + "=" + slider_current_value);
	 slider_previous_value = slider_current_value;

	 }*/
}

function turn_auto_suggest() {

	var suggest_words_arr = search_words;

	$("#cruxpad_search_words").autocompleteArray(suggest_words_arr, {
		autoFill : true,
		delay : map_mini_footer_other_constants['search_delay_autosuggest']
	});
}

function process_search_word(searched_word) {

	/* Need to check the entered word in the article and in case if doesn't matches need to call the function below to display
	 * error message
	 */
	log_console_msg('searched_word :' + searched_word);
	footer_searchword_input_used_cnt++;
	if (searched_word === '') {
		show_footer_msg_div(map_mini_footer_msgs['search_text_blank_msg']);
		setTimeout(hide_footer_msg_div, map_mini_footer_other_constants['msg_timeout_interval_value']);
		send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_change_footer_size_msg']);
	} else {
		if (check_search_word_exists(searched_word)) {// Show the main dialog with the selected word
			crux_searchword_to_dialog_open_cnt++;
			send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_footer_select_keyword'] + searched_word);
		} else {// Show the error message in the footer.
			show_footer_msg_div(map_mini_footer_msgs['search_text_invalid_msg']);
			setTimeout(hide_footer_msg_div, map_mini_footer_other_constants['msg_timeout_interval_value']);
			send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_change_footer_size_msg']);
		}
	}
}

function assign_title_msgs_footer() {
	$('#cruxpad_mini_footer_logo').attr("title", map_mini_footer_msgs['cruxlight_logo_hover_msg']);
	$('#cruxpad_mini_footer_close').attr("title", map_mini_footer_msgs['close_hover_msg']);
	$('#cruxpad_footer_cruxview_button').attr("title", map_mini_footer_msgs['cruxview_button_hover_msg']);
	$('#cruxpad_mini_footer_slider').attr("title", map_mini_footer_msgs['slider_hover_msg']);
	$('#cruxpad_mini_footer_color_picker').attr("title", map_mini_footer_msgs['color_picker_hover_msg']);
	$('#cruxpad_mini_footer_facebook_share_img').attr("title", map_mini_footer_msgs['facebook_share_hover_msg']);
	$('#cruxpad_mini_footer_twitter_share_img').attr("title", map_mini_footer_msgs['twitter_share_hover_msg']);
}

function show_footer_msg_div(msg_to_show) {
	$("#cruxpad_mini_footer_msg_div").html(msg_to_show);
	$("#cruxpad_mini_footer_msg_div").css({
		"display" : "block"
	});
}

function hide_footer_msg_div() {
	$("#cruxpad_mini_footer_msg_div").fadeOut(map_mini_footer_other_constants['msg_fadeout_interval_value']);

	// New message event added by Vikas on 20th Dec'12 to handle the change in wrapper size when search text error message is faded out.
	setTimeout(change_footer_height_normal, map_mini_footer_other_constants['msg_fadeout_interval_value']);
}

function change_footer_height_normal() {

	/* Added by Vikas onn 2oth Dec'12 to send message to content script only when
	 * footer is visible, else no need to change wrapper size
	 */

	if ($("#cruxpad_mini_footer").is(':visible')) {
		send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_change_footer_size_normal_msg']);
	}
}

function check_search_word_exists(searched_word) {

	var flag = false;

	var parts = searched_word.split(new RegExp(" |_", ""));

	$.each(all_sentences, function(index, sent) {
		var t_sent = sent.sentence;
		for (var j = 0; j < parts.length; j++) {
			if (parts[j].length > 1) {
				if ((t_sent.toLowerCase().indexOf(parts[j].toLowerCase()) != -1)) {
					flag = true;
					break;
				}
			}
		}

	});

	// Check to see if the entered word exists in the article. If yes, return true else false.
	return flag;
}

/*********************************FOOTER FUNCTIONS ENDS ***************** */

/*********************************** The Execution starts from here *********************************/

window.onload = function() {
	log_console_msg('Execution Starts');
	var ajax_url, ajax_data;
	if (document.getElementById('view_summary_flag')) {
		url = get_url_params('?url=', '&&source=');
		view_source = get_url_params('&&source=', '');
		ajax_url = map_cruxpad_urls['view_summary_req_url'];
		ajax_data = "url=" + url + "&&source=" + view_source;
	} else {
		url = get_url_params('?url=', '&&pluginId=');
		plugin_id = get_url_params('&&pluginId=', '&&popupDisableFlag=');
		disable_popup_flag = get_url_params('&&popupDisableFlag=', '&&theme=');
		dialog_display_theme = get_url_params('&&theme=', '');
		ajax_url = map_cruxpad_urls['summary_req_url'];
		ajax_data = "url=" + encodeURIComponent(url) + "&&pluginId=" + plugin_id;
	}
	log_console_msg('   Document URL :' + url);
	get_json_object(ajax_url, ajax_data, dialog_display_theme);

}

$(document).ready(function() {
	jQuery(".cruxpad_feedback_a").toggle(function() {
		jQuery("#cruxpad_feedback_div").animate({
			right : "0px"
		});
		if (!recaptcha_load_flag) {
			show_recaptcha('cruxpad_recaptcha_div');
			recaptcha_load_flag = true;
		}
		feedback_click_cnt++;
		return false;
	}, function() {
		jQuery("#cruxpad_feedback_div").animate({
			right : "-600px"
		});
		return false;
	});
});

/*********************************** FUNCTIONs HANDLING SUMMARY STARTS ***********************/
/*
 * This function makes an ajax call for summary
 */
function get_json_object(ajax_url, ajax_data, dialog_display_theme) {
	log_console_msg("summary.html :: function get_json_object : starts");
	$.ajax({
		url : ajax_url,
		type : "POST",
		cache : true,
		data : ajax_data,
		error : function(jqXHR, textStatus, errorThrown) {
			log_console_msg(errorThrown);
		},
		success : function(data) {
			log_console_msg('   GOT Json Data from server');
			data = JSON.parse(JSON.stringify(data));
			bind_json_object(data, dialog_display_theme);
		}
	});
	log_console_msg("summary.html :: function get_json_object : ends");
}

/*
 * This function stores the data obtained from json object in json variable
 */
function bind_json_object(jsonObject, dialog_display_theme) {

	//JSON parsing is compulsory because it protects us from executing malicious script
	// introduced by the attacker
	json_response = jsonObject;
	jsonObject = null;
	log_console_msg("summary.html :: function bind_json_object : starts");

	//log_console_msg(JSON.stringify(json_response));

	summary_status = json_response.summaryFlag;

	if (summary_status == "Y") {
		if (dialog_display_theme && dialog_display_theme !== map_cruxpad_themes_code['default_theme']) {
			change_dialog_theme_css(dialog_display_theme);
		}
		dialog_main();
		if (document.getElementById('view_summary_flag')) {
			$("#cruxpad_dialog").show();
			set_dialog_window();
			assign_view_summary_events();
			document.title = 'CruxLight Summary: ' + article_title;
			$("#cruxpad_download_link").attr("href", server_base_url);
			set_download_cruxlight_text_width();
		} else {
			assign_dialog_events();
			footer_main();
			increment_footer_active_cnt_analytics();
			handle_highlight_summary_call(map_mini_footer_slider_constants['slider_highlight_default_start_index'], map_mini_footer_slider_constants['slider_init_marker_value'], $("#color_picker").val(), true);
		}

	} else {// if we dont get summary due to any technical reason
		if (document.getElementById('view_summary_flag')) {
			window.location.href = map_cruxpad_urls['view_summary_msg_link'] + '?errorMsg=' + json_response.message;
		} else {
			send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_summary_error_msg'] + '-' + json_response.message);
		}
	}
	log_console_msg("summary.html :: function bind_json_object : ends");

}

function set_download_cruxlight_text_width() {
	var download_text_width = $("#download_cruxlight_text").outerWidth();
	var dialog_left_width = $("#cruxpad_dialog_view").outerWidth();
	var short_view_width = $("#short_view").outerWidth();
	var medium_view_width = $("#medium_view").outerWidth();
	var original_view_width = $("#original_view").outerWidth();

	if (dialog_left_width - download_text_width - short_view_width - medium_view_width - original_view_width < 50) {
		$("#download_cruxlight_text").html("CruxLight automatically summarizes web pages for you !");
	}

}

function change_dialog_theme_css(dialog_display_theme) {
	if (dialog_display_theme === map_cruxpad_themes_code['theme1_theme']) {
		$("#cruxpad_dialog_main_header").addClass("cruxpad_dialog_main_header_theme1");
		$("#cruxpad_dialog_header").addClass("cruxpad_dialog_header_theme1");
		$("#cruxpad_dialog_img").addClass("cruxpad_dialog_img_theme1");
		$("#cruxpad_dialog_close").addClass("cruxpad_dialog_close_theme1");
		$("#cruxpad_dialog").addClass("cruxpad_dialog_theme1");
		$("#cruxpad_dialog_summary").addClass("cruxpad_dialog_summary_theme1");
		$("#cruxpad_dialog_title").addClass("cruxpad_dialog_title_theme1");
		$("#cruxpad_dialog_page").addClass("cruxpad_dialog_page_theme1");
		$("#cruxpad_dialog_right").addClass("cruxpad_dialog_right_theme1");
		$("#cruxpad_dialog_footer").addClass("cruxpad_dialog_footer_theme1");
		$("#cruxpad_dialog_view").addClass("cruxpad_dialog_view_theme1");
		// $("#cruxpad_body").addClass("cruxpad_body_css_theme1");
		var screen_width = get_screen_width();
		var dialog_width = $('#cruxpad_dialog').width();
		if (parseInt(screen_width) > parseInt(dialog_width)) {

			dialog_left_position = parseInt((screen_width - dialog_width) / 2);

			$('#cruxpad_dialog').css({
				"left" : dialog_left_position + "px"
			});

			var fb_msg_div_left_position = dialog_left_position + parseInt($('#fb_msg_div').css("left").replace('px', ''));
			var fb_frame_left_position = dialog_left_position + parseInt($('#fb_frame').css("left").replace('px', ''));

			$('#fb_msg_div').css({
				"left" : fb_msg_div_left_position + "px"
			});

			$('#fb_frame').css({
				"left" : fb_frame_left_position + "px"
			});

			var gp_msg_div_left_position = dialog_left_position + parseInt($('#gp_msg_div').css("left").replace('px', ''));
			var gp_frame_left_position = dialog_left_position + parseInt($('#gp_frame').css("left").replace('px', ''));

			$('#gp_msg_div').css({
				"left" : gp_msg_div_left_position + "px"
			});

			$('#gp_frame').css({
				"left" : gp_frame_left_position + "px"
			});

			$('#cruxpad_dialog_footer').css({
				"left" : dialog_left_position + "px",
				"border-left" : "1px solid #000"
			});

			$('#cruxpad_dialog_view').css({
				"left" : dialog_left_position + "px",
				"border-left" : "1px solid #000"
			});
		}
	}
}

function dialog_main() {

	sentences_cnt = json_response.sentencesCnt;
	original = json_response.origText;
	keyphrases_cnt = json_response.keyPhrasesCnt;
	keyphrases = json_response.keyPhrases;
	keyentities_cnt = json_response.keyEntitiesCnt;
	keyentities = json_response.keyEntities;
	domain_name = json_response.domainName;
	all_sentences = (json_response.sentences);
	article_title = json_response.title;

	search_words = json_response.searchWordsSuggArrList;

	log_console_msg("search words :- " + JSON.stringify(search_words));

	log_console_msg("   Keyphrases :-" + JSON.stringify(keyphrases));
	log_console_msg("   Keyentities :-" + JSON.stringify(keyentities));
	log_console_msg('   Domain Name: ' + domain_name);

	var chunk_regex = new RegExp(json_response.chunkAppSepStr, 'g');

	var wiki_regex = /\[(edit|(0-9)+)\]/gi;

	var sent_d_a = [];

	$.each(all_sentences, function(index, sent) {
		sent.sentence = sent.sentence.replace(chunk_regex, " ").replace(wiki_regex, '');

		if (index < 7) {
			sent_d_a.push(sent);
		}
		sent_d_aoriginal.push(sent);
	});

	sent_d_a.sort(sort_asc_order);
	sent_d_aoriginal.sort(sort_asc_order);
	$.each(sent_d_a, function(index, sent) {
		short_summary += "  " + escape_for_html(sent.sentence);
	});

	twenty_percent_sent_no = sentences_cnt * 0.20;
	forty_percent_sent_no = sentences_cnt * 0.40;

	if (twenty_percent_sent_no < 6) {
		twenty_percent_sent_no = 7;
	}

	if (forty_percent_sent_no < 12) {
		forty_percent_sent_no = 13;
	}

	$.each(all_sentences, function(index, sent) {
		if (index < twenty_percent_sent_no) {
			sent_d_a20.push(sent);
			summary_words += (((sent.sentence).split(" ")).length);
		}
		total_words += ((sent.sentence).split(" ").length);
	});

	// time saved calculation
	time_saved_in_minutes = (total_words - summary_words) / avg_words_per_minute;

	if (time_saved_in_minutes <= 0.1) {
		$("#time_saved").hide();
	} else {
		var integer = Math.floor(time_saved_in_minutes);
		var deci = Math.floor((time_saved_in_minutes % 1) * 60);
		if (deci < 10) {
			deci = "0" + deci;
		}
		$('#time_saved_text').html(' Time Saved: ' + integer + ':' + deci + ' mins.');
	}
	sent_d_a20.sort(sort_asc_order);
	current_content = sent_d_a20;
	$.each(sent_d_a20, function(index, sent) {
		twenty_per_summary += "<p>" + escape_for_html(sent.sentence) + "</p>";
		short_summary_share += sent.sentence + ' ';
	});

	// forty percent summary
	$.each(all_sentences, function(index, sent) {
		if (index < forty_percent_sent_no) {
			sent_d_a40.push(sent);
		}

	});
	sent_d_a40.sort(sort_asc_order);

	$.each(sent_d_a40, function(index, sent) {
		forty_per_summary += "<p>" + escape_for_html(sent.sentence) + "</p>";
	});
	original_para_parts = original.split("\n");
	original = "";
	for ( i = 0; i < original_para_parts.length; i++) {
		original += '<p>' + escape_for_html(original_para_parts[i]) + '</p>';
	}

	// settingg the HTML content of all the empty elements

	$("#favicon_domain").attr("src", "http://www.google.com/s2/favicons?domain=" + domain_name);
	$("#cruxpad_dialog_link").text(domain_name + ' ( Back to Article )');

	$("#cruxpad_dialog_content").html(twenty_per_summary);

	long_summary_keywords = "<span class='key_label'>FOCUS</span><ul class='l_key'>";

	$.each(keyentities, function(index, key) {
		if (key != null) {
			twitter_keywords += key.word + ",";
			long_summary_keywords += "<li><div class='cruxpad_li_keywords'>" + key.word + "</div></li>";
		}

	});

	$.each(keyphrases, function(index, key) {
		if (key != null) {
			twitter_keywords += key.word + ",";
			long_summary_keywords += "<li><div class='cruxpad_li_keywords'>" + key.word + "</div></li>";
		}
	});

	twitter_keywords = twitter_keywords.substring(0, twitter_keywords.length - 1);

	long_summary_keywords += "</ul>";

	// setting the html content of all the divs
	$("#cruxpad_dialog_title").html(article_title);
	$("#cruxpad_dialog_title").attr("title", "Article Title: " + article_title);

	$("#cruxpad_dialog_key").html(long_summary_keywords);
	$("#cruxpad_dialog_content").html(twenty_per_summary);

	// This code gives hover effect to keyword selection
	$(".cruxpad_li_keywords").hover(function() {
		$(this).css({
			"background-color" : "gainsboro"
		});
	}, function() {
		if ($(this).text() != selected_keyword) {
			$(this).css({
				"background-color" : "white"
			});
		} else {
			$(this).css({
				"background-color" : "gainsboro"
			});
		}
	});

	$(".short_view").live("click", function() {
		selected_keyword = "";
		$(".cruxpad_li_keywords").each(function(index, key) {
			if ($(key).text() === $(this).text()) {
				$(this).css({
					"background-color" : "white"
				});
			}
		});
		current_content = sent_d_a20;
		$(".medium_view").css({
			"background-color" : "white"
		});
		$(".short_view").css({
			"background-color" : "gainsboro"
		});
		$(".original_view").css({
			"background-color" : "white"
		});

		$("#cruxpad_dialog_content").html(twenty_per_summary);
		reset_scroll_bar();
		show_timesave_text(true);
	});

	$(".medium_view").live("click", function() {

		selected_keyword = "";
		$(".cruxpad_li_keywords").each(function(index, key) {
			if ($(key).text() === $(this).text()) {
				$(this).css({
					"background-color" : "white"
				});
			}
		});
		current_content = sent_d_a40;
		$(".medium_view").css({
			"background-color" : "gainsboro"
		});
		$(".short_view").css({
			"background-color" : "white"
		});
		$(".original_view").css({
			"background-color" : "white"
		});

		$("#cruxpad_dialog_content").html(forty_per_summary);

		reset_scroll_bar();
		show_timesave_text(false);
	});

	$(".original_view").live("click", function() {
		// window.location = "#cruxpad_dialog_content";
		selected_keyword = "";
		$(".cruxpad_li_keywords").each(function(index, key) {
			if ($(key).text() === $(this).text()) {
				$(this).css({
					"background-color" : "white"
				});
			}
		});

		$("#cruxpad_dialog_content").html(original);
		$(".medium_view").css({
			"background-color" : "white"
		});
		$(".short_view").css({
			"background-color" : "white"
		});
		$(".original_view").css({
			"background-color" : "gainsboro"
		});
		reset_scroll_bar();
		show_timesave_text(false);
	});

	$(".cruxpad_li_keywords").live("click", function() {
		handle_change_in_keyword_selection($(this).text());
	});

	// initialization task
	$("#cruxpad_mini_footer").show();
	$("#cruxpad_mini_footer_msg_palette_div").show();
	$("#cruxpad_dialog").hide();

	$(".short_view").css({
		"background-color" : "gainsboro"
	});

	assign_dialog_attr();
}

function assign_dialog_attr() {
	$("#cruxpad_help_id").attr('href', map_cruxpad_urls['cruxlight_help_url']);
	$("#cruxpad_main_dialog_logo").attr('href', server_base_url);
}

function handle_highlight_summary_call(highlight_start_per, highlight_end_per, highlight_color, highlight_init_flag) {

	if (Math.abs(highlight_end_per - highlight_start_per) >= parseInt(map_mini_footer_slider_constants['slider_event_fire_on_previous_current_diff'])) {// If the percentage diff. criteria is satisfied

		var highlight_start_index = Math.floor((highlight_start_per * json_response.sentencesCnt) / 100);
		var highlight_end_index = Math.floor((highlight_end_per * json_response.sentencesCnt) / 100);

		if (Math.abs(highlight_end_index - highlight_start_index) >= parseInt(map_mini_footer_slider_constants['slider_event_fire_prev_curr_sent_diff'])) {// If the difference in no. of sentences to highlight is satisfied

			if (highlight_init_flag) {
				json_response.highlight_start_index = highlight_start_index;
				json_response.highlight_end_index = highlight_end_index;
				json_response.highlight_color = highlight_color;
				json_response.msg_seperator_special_char = map_other_constants['msg_seperator_special_char'];
				json_response.time_saved = get_time_saved_in_seconds();
				log_console_msg('Init message sent');
				send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_show_iframe_msg'] + "-" + JSON.stringify(json_response));
			} else {
				log_console_msg('Change message sent');
				send_msg_to_content_script(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_slider_change_msg'] + "-" + highlight_start_index + map_other_constants['msg_seperator_special_char'] + highlight_end_index + map_other_constants['msg_seperator_special_char'] + highlight_color);
			}
			if (highlight_color === map_mini_footer_other_constants['color_picker_transparent_color_code']) {
				slider_previous_value = highlight_start_per;
			} else {
				slider_previous_value = highlight_end_per;
			}
			footer_slider_change_cnt++;
			log_console_msg('Highlight Slider Previous Value: ' + slider_previous_value);
		}
	}
}

/*********************************** FUNCTIONs HANDLING SUMMARY ENDS **********************/

/********************************* DIALOG FUNCTIONS START ***************** */

function assign_view_summary_events() {
	$("#cruxpad_dialog_link").live("click", function() {
		window.location.href = decodeURIComponent(url);
	});
}

function handle_change_in_keyword_selection(keyword, reset_scroll_flag) {

	log_console_msg('summary.js :: function handle_change_in_keyword_selection starts');

	if ( typeof reset_scroll_flag == "undefined") {
		reset_scroll_flag = true;
	}
	$(".cruxpad_li_keywords").each(function(index, key) {

		if ($(key).text().toLowerCase() == keyword.toLowerCase()) {
			$(key).css({
				"background-color" : "gainsboro"
			});
		} else {
			$(this).css({
				"background-color" : "white"
			});
		}
	});

	set_focus_keyword(keyword, reset_scroll_flag);
}

/*
 * This function selects all the text of the given element as an argument when
 * user clicks on select all option in dialog
 */
function select_text(element) {
	log_console_msg('summary.html :: function select_text : starts');

	select_all_cnt++;

	var doc = document;
	var text = doc.getElementById(element);

	if (doc.body.createTextRange) {// ms
		var range = doc.body.createTextRange();
		range.moveToElementText(text);
		range.select();
	} else if (window.getSelection) {// moz, opera, webkit
		var selection = window.getSelection();
		var range = doc.createRange();
		range.selectNodeContents(text);
		selection.removeAllRanges();
		selection.addRange(range);
	}
	log_console_msg('function select_text : ends');
}

/*
 * This function selects all the sentences which contains the selected keywords
 */
function set_focus_keyword(keyword, reset_scroll_flag) {
	log_console_msg('function set_focus_keyword : starts');
	selected_keyword = keyword;
	var tempsum = "";
	var parts = keyword.split(new RegExp(" |_", ""));
	/* added by ashish to include related words */
	var arr_split_related_words = get_related_words(keyword);
	var i = 0;
	for ( i = 0; i < arr_split_related_words.length; i++) {
		if (parts.indexOf(arr_split_related_words[i]) < 0)
			parts.push(arr_split_related_words[i]);
	}
	/* added by ashish to include related words */

	$.each(sent_d_aoriginal, function(index, sent) {
		var t_sent = sent.sentence;
		for (var j = 0; j < parts.length; j++) {
			if (parts[j].length > 1 && (t_sent.toLowerCase().indexOf(parts[j].toLowerCase()) != -1)) {
				tempsum += "<p>" + escape_for_html(t_sent) + "</p>";
				break;
			}
		}

	});
	document.getElementById("cruxpad_dialog_content").innerHTML = tempsum;

	// remove selection effect of short ,medium and original
	$(".medium_view").css({
		"background-color" : "white"
	});
	$(".short_view").css({
		"background-color" : "white"
	});
	$(".original_view").css({
		"background-color" : "white"
	});

	highlight_keyword(keyword);
	show_timesave_text(false);
	if (reset_scroll_flag) {
		reset_scroll_bar();
	}

	log_console_msg('function set_focus_keyword : ends');
}

/*
 * This function highlights all the sentences based on keyword selection
 */
function highlight_keyword(keyword) {
	log_console_msg('function highlight_keyword : starts');
	// remove previous selection effects
	var i;
	var element = document.getElementById("cruxpad_dialog_content");

	var full_start = new RegExp('<c_r_u_x_f_u_l_l>', 'g');
	var half_start = new RegExp('<c_r_u_x_h_a_l_f>', 'g');

	var full_end = new RegExp('</c_r_u_x_f_u_l_l>', 'g');
	var half_end = new RegExp('</c_r_u_x_h_a_l_f>', 'g');

	element.innerHTML = element.innerHTML.replace(full_start, "");
	element.innerHTML = element.innerHTML.replace(half_start, "");
	element.innerHTML = element.innerHTML.replace(full_end, "");
	element.innerHTML = element.innerHTML.replace(half_end, "");

	// applying new effects
	var full_match_reg_exp = new RegExp('\\b(' + keyword + ')\\b', 'gi');
	//\b matches any non-word boundaries
	var full_match_repl = '<c_r_u_x_f_u_l_l>$1</c_r_u_x_f_u_l_l>';

	var half_match_reg_exp;
	var half_match_repl;

	element.innerHTML = element.innerHTML.replace(full_match_reg_exp, full_match_repl);
	var parts = keyword.split(new RegExp(" |_", ""));

	/* added by ashish to include related words */
	var arr_split_related_words = get_related_words(keyword);
	var i = 0;
	for ( i = 0; i < arr_split_related_words.length; i++) {
		if (parts.indexOf(arr_split_related_words[i]) < 0) {
			parts.push(arr_split_related_words[i]);
		}
	}
	/* added by ashish to include related words */

	parts.sort(function(word1, word2) {
		return word2.length - word1.length
	});
	for ( i = 0; i < parts.length; i++) {
		if (parts[i].length > 1) {
			half_match_reg_exp = new RegExp('(' + parts[i] + ')', 'gi');
			half_match_repl = '<c_r_u_x_h_a_l_f>$1</c_r_u_x_h_a_l_f>';
			element.innerHTML = element.innerHTML.replace(half_match_reg_exp, half_match_repl);
		}
	}

	log_console_msg('function highlight_keyword : ends');
}

function get_related_words(keyword) {
	var i = 0;
	for ( i = 0; i < keyentities.length; i++) {
		if (keyentities[i] !== null && keyentities[i].word === keyword && keyentities[i].relatedWords !== null && keyentities[i].relatedWords.length > 0) {
			return keyentities[i].relatedWords.split(',');
		}
	}

	for ( i = 0; i < keyphrases.length; i++) {
		if (keyphrases[i] !== null && keyphrases[i].word === keyword && keyphrases[i].relatedWords !== null && keyphrases[i].relatedWords.length > 0) {
			return keyphrases[i].relatedWords.split(',');
		}
	}
	return [];
}

function reset_scroll_bar() {
	$("#cruxpad_dialog_page").mCustomScrollbar("update");
	$("#cruxpad_dialog_page").mCustomScrollbar("scrollTo", "top");
}

/*
 * This function sets the width of the content div, header and footer of dialog
 * dynamically
 */
function set_dialog_window() {
	log_console_msg('summary.html :: function  set_dialog_window : starts');
	var height = get_screen_height();
	var dialog_hdr_height = $("#cruxpad_dialog_header").height();
	var dialog_footer_height = $("#cruxpad_dialog_footer").height();
	var dialog_view_height = $("#cruxpad_dialog_view").height();
	var dialog_title_height = $("#cruxpad_dialog_title").height();
	var content_height = height - dialog_hdr_height - dialog_footer_height - dialog_view_height - dialog_title_height - 80;

	$('#cruxpad_dialog_page').css({
		"height" : content_height + "px"
	});

	var dialog_right_top = dialog_hdr_height + dialog_title_height + 40;
	$('#cruxpad_dialog_right').css({
		"top" : dialog_right_top + "px"
	});

	if (!scrollbar_init_flag) {
		$("#cruxpad_dialog_page").mCustomScrollbar();
		scrollbar_init_flag = true;
	}
	log_console_msg('summary.html :: function  set_dialog_window : ends');
}

/********************************* DIALOG FUNCTIONS END******************/

/********************************** FUNCTION SENDING MESSAGE TO CONTENT SCRIPT START ******************/
function send_msg_to_content_script(msg) {

	parent.postMessage(msg, url);
	if (msg == map_cruxpad_iframe_extensions_sent_msgs['cruxpad_footer_cruxpad_view_msg']) {
		handle_footer_dialog_transition();
		crux_footer_to_dialog_open_cnt++;
		crux_dialog_active_cnt++;
	} else if (msg.match(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_footer_select_keyword'])) {
		var keyword = msg.substring(map_cruxpad_iframe_extensions_sent_msgs['cruxpad_footer_select_keyword'].length);
		handle_footer_dialog_transition();
		crux_dialog_active_cnt++;
		handle_change_in_keyword_selection(keyword, false);
	} else if (msg == map_cruxpad_iframe_extensions_sent_msgs['cruxpad_footer_close_msg']) {
		reinit_slider_footer_close();
		handle_footer_close();
	}

}

function handle_footer_dialog_transition() {
	handle_footer_close();
	$("#cruxpad_dialog").show();
	handle_body_background_themes();
	set_dialog_window();
}

function reinit_slider_footer_close() {
	$("#cruxpad_mini_footer_slider").slider("value", map_mini_footer_slider_constants['slider_min_marker_value']);
	slider_previous_value = map_mini_footer_slider_constants['slider_min_marker_value'];
}

function handle_footer_close() {
	$("#cruxpad_mini_footer").hide();
	$("#cruxpad_mini_footer_msg_palette_div").hide();
	$('.colorPicker-palette').hide();
}

function handle_footer_dialog_close() {
	handle_footer_close();
	$("#cruxpad_dialog").hide();
}

/********************************** FUNCTION SENDING MESSAGE TO CONTENT SCRIPT END ******************/

/********************************** ALL SHARING AND SAVING FUNCTIONS STARTS *******************/

/**
 * This function for facebook sharing.
 *
 * @author Yash
 */
function fb_share() {
	if ($("#cruxpad_dialog").is(':visible')) {
		facebook_shared_cnt++;
	} else if ($("#cruxpad_mini_footer").is(':visible') && disable_popup_flag == 'N') {
		footer_facebook_share_cnt++;
	}
	log_console_msg('Facebook Dialog Share Count: ' + facebook_shared_cnt);
	log_console_msg('Facebook Footer Share Count: ' + footer_facebook_share_cnt);

	var share_url = encodeURIComponent(map_cruxpad_urls['view_summary_share_link'] + '?url=' + url + '&&source=fb');
	var link = 'https://www.facebook.com/sharer.php?s=100&p[title]=CruxLight Summary : ' + article_title + ' !&p[url]=' + share_url + '&p[images][0]=' + server_base_url + '/images/cruxlight-logo-image.png&p[summary]=' + short_summary_share.substring(0, 219) + '... --> Read Less , Save Time <--';
	window.open(link, '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=700');
}

/**
 * This function for google sharing.
 *
 * @author Yash
 */

function google_share() {
	googleplus_shared_cnt++;
	var link = 'CruxLight Summary: Article Title -> ' + article_title + '. Summary Link -> ' + map_cruxpad_urls['view_summary_share_link'] + '?url=' + url + '&&source=gp';
	// log_console_msg(link);
	window.open('https://m.google.com/app/plus/x/?v=compose&content=' + encodeURIComponent(link), '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=900');
}

function report_feedback() {
	if (!is_feedback_given) {
		if (dialog_display_theme === map_cruxpad_themes_code['default_theme']) {
			notify_bottom_right(cruxpad_dialog_notification_msg.cruxpad_feedback_success_msg, map_notify_msg_position['feedback_bottom'], map_notify_msg_position['feedback_right']);
		} else if (dialog_display_theme === map_cruxpad_themes_code['theme1_theme']) {
			notify_bottom_right(cruxpad_dialog_notification_msg.cruxpad_feedback_success_msg, map_notify_msg_position['feedback_bottom'], parseInt(map_notify_msg_position['feedback_right']) + parseInt(dialog_left_position));
		}
		//notify_bottom_right(cruxpad_dialog_notification_msg.cruxpad_feedback_success_msg, '40px', 0);
		is_feedback_given = true;
		is_reported_bad_summary = "Y"
	} else {
		if (dialog_display_theme === map_cruxpad_themes_code['default_theme']) {
			notify_bottom_right(cruxpad_dialog_notification_msg.cruxpad_feedback_already_given_msg, map_notify_msg_position['feedback_bottom'], map_notify_msg_position['feedback_right']);
		} else if (dialog_display_theme === map_cruxpad_themes_code['theme1_theme']) {
			notify_bottom_right(cruxpad_dialog_notification_msg.cruxpad_feedback_already_given_msg, map_notify_msg_position['feedback_bottom'], parseInt(map_notify_msg_position['feedback_right']) + parseInt(dialog_left_position));
		}
		//notify_bottom_right(cruxpad_dialog_notification_msg.cruxpad_feedback_already_given_msg, '40px', 0);
	}
}

function print_summary() {

	print_summary_cnt++;
	var print_article_title = $("#cruxpad_dialog_title").html();
	var print_article_content = $("#cruxpad_dialog_content").html();

	var line_break_html = '<br>';

	var printable_content_html = '';

	var print_article_keywords = twitter_keywords.replace(new RegExp(',', 'g'), ', ');

	log_console_msg('Twitter Keywords: ' + twitter_keywords);

	printable_content_html += '<div id = print_header class = cruxpad_print_header><h3><img src=../../images/logo-main-dialogue.png /></h3></div>';
	printable_content_html += '<h1 class = cruxpad_print_title>' + print_article_title + '</h1>';
	printable_content_html += '<h3 class = cruxpad_print_keywords>Keywords: ' + print_article_keywords + '</h3>';
	printable_content_html += '<div id = print_content class = cruxpad_print_content>' + print_article_content + '</div>';

	var print_iframe = document.createElement("iframe");
	print_iframe.setAttribute("id", "cruxpad_printframe");
	document.getElementsByTagName("body")[0].appendChild(print_iframe);

	print_iframe = document.getElementById("cruxpad_printframe");
	var print_doc = (print_iframe.contentDocument || print_iframe.contentWindow.document);

	print_doc.open();
	print_doc.write(printable_content_html);
	print_doc.close();

	print_iframe.contentWindow.print();

	/*modified by ashish for printing in firefox starts*/
	setTimeout(function() {
		$("#cruxpad_printframe").remove();
	}, map_cruxpad_dialog_constants['print_timeout_milliseconds']);
	/*modified by ashish for printing in firefox ends*/
}

/**
 * This function for twitter sharing. It first removes # from the title if any.
 * Then the logic of setting the 140 characters limit of twitter is implemented.
 *
 * @author Yash
 */

function twitter_share() {

	if ($("#cruxpad_dialog").is(':visible')) {
		twitter_shared_cnt++;
	} else if ($("#cruxpad_mini_footer").is(':visible') && disable_popup_flag == 'N') {
		footer_twitter_share_cnt++;
	}

	log_console_msg('Twitter Dialog Share Count: ' + twitter_shared_cnt);
	log_console_msg('Twitter Footer Share Count: ' + footer_twitter_share_cnt);

	// temp copy is made becasue if the user wants to share the link 2nd time it
	// will create problem.
	var twitter_keywords_temp = twitter_keywords;

	var twitt_max_chars = 140;
	// Max length of twitt
	var twitt_link_chars = 22;
	// 20(http) && 21(https) for URL and 1 for default space that twitter takes.
	var twitt_hashtags_max_chars = 40;
	// 40 is the max limit. This includes the special symbol hash also which is not passed from here but taken by twitter. Also one needs to seperate the hash words by commas. The last word also needs to have comma in the end. Twitter replaces commas with space so calculation is based considering commas as number of commas will be replaced by spaces.
	var twitt_text_chars;
	var twitt_hashtags_cnt = 0;

	// removing # from article title
	var twitt_article_title = article_title.replace(/#/g, '');

	var other_keywords = twitter_keywords_temp.split(',');

	twitter_keywords_temp = "CruxLight,";
	twitt_hashtags_cnt++;

	/* Added by Vikas for the problem when article contains query string the text after '?' is considered to be the part of text and not url. So ending article url with '/' to solve the problem. */

	var article_url_twitter = url;

	// if (article_url_twitter.indexOf('?') != -1) {
	// article_url_twitter = article_url_twitter + '/';
	// }
	log_console_msg('Article URL Twitter: ' + article_url_twitter);

	var i = 0;
	while (i < other_keywords.length) {
		other_keywords[i] = other_keywords[i].replace(/\s/g, "");
		log_console_msg('Left side: ' + parseInt(twitter_keywords_temp.length + other_keywords[i].length + twitt_hashtags_cnt + 1));
		log_console_msg('Right side:' + parseInt(twitt_hashtags_max_chars));
		if (parseInt(twitter_keywords_temp.length + other_keywords[i].length + twitt_hashtags_cnt + 1) < parseInt(twitt_hashtags_max_chars)) {
			twitter_keywords_temp += other_keywords[i] + ',';
			twitt_hashtags_cnt++;
		}
		i++;
	}

	twitt_text_chars = twitt_max_chars - twitt_link_chars - twitter_keywords_temp.length - twitt_hashtags_cnt;

	twitt_article_title = 'CruxLight Summary: ' + twitt_article_title;

	if (twitt_article_title.length > twitt_text_chars) {
		twitt_article_title = twitt_article_title.substring(0, twitt_text_chars - 4) + '...';
	}

	log_console_msg('Hashtags: ' + twitter_keywords_temp);
	log_console_msg('Hashtags Length: ' + parseInt(twitter_keywords_temp.length + twitt_hashtags_cnt));
	log_console_msg('Text: ' + twitt_article_title);
	log_console_msg('Text Length: ' + twitt_article_title.length);
	log_console_msg('Max Text Length: ' + twitt_text_chars);

	var share_url = map_cruxpad_urls['view_summary_share_link'] + '?url=' + article_url_twitter + '&&source=tw';

	log_console_msg('Twitter Share URL: ' + share_url);
	var link = "https://twitter.com/intent/tweet?original_referer=http://www.cruxbot.com&text=" + twitt_article_title + "&url=" + encodeURIComponent(share_url) + "&hashtags=" + twitter_keywords_temp;
	window.open(link, '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=700');
}

/**
 * This function is used for saving summary to pocket.
 *
 * @author Yash
 */

function save_to_pocket() {
	save_pocket_cnt++;
	var link = map_cruxpad_urls['getpocket_share_url'] + '?articleTitle=' + article_title + '&&articleLink=' + url + '&&pluginId=' + plugin_id;
	window.open(link, '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=700');
	window.focus();
}

/**
 * This function is used for saving summary to evernote.
 *
 * @author Yash
 */

function save_to_evernote() {
	if (dialog_display_theme === map_cruxpad_themes_code['default_theme']) {
		notify_bottom_left(cruxpad_dialog_notification_msg.evernote_connect_msg, map_notify_msg_position['evernote_bottom'], map_notify_msg_position['evernote_left']);
	} else if (dialog_display_theme === map_cruxpad_themes_code['theme1_theme']) {
		notify_bottom_left(cruxpad_dialog_notification_msg.evernote_connect_msg, map_notify_msg_position['evernote_bottom'], parseInt(map_notify_msg_position['evernote_left']) + parseInt(dialog_left_position) - parseInt(map_notify_msg_position['evernote_theme1_decrement_value']));
	}
	$("#cruxpad_notify_msg").show();

	var link = server_base_url + '/evernoteShare';
	$.ajax({
		url : link,
		type : "POST",
		cache : true,
		async : false,
		data : "pluginId=" + plugin_id + "&&action=checkEvernoteStatus",
		error : function(jqXHR, textStatus, errorThrown) {
			log_console_msg("There is some technical error !! Please try after some time.");
		},
		success : function(data) {
			if (data.statusFlag == "Y") {
				save_evernote_cnt++;
				var link = server_base_url + '/evernoteShare';
				$.ajax({
					url : link,
					type : "POST",
					cache : true,
					data : "action=saveSummary&&pluginId=" + plugin_id + "&&summary=" + encodeURIComponent(short_summary_share) + "&&urlTitle=" + article_title + "&&articleUrl=" + encodeURIComponent(url),
					error : function(jqXHR, textStatus, errorThrown) {
						$("#cruxpad_notify_msg").hide();
						if (dialog_display_theme === map_cruxpad_themes_code['default_theme']) {
							notify_bottom_left(cruxpad_dialog_notification_msg.technical_error_msg, map_notify_msg_position['evernote_bottom'], map_notify_msg_position['evernote_left']);
						} else if (dialog_display_theme === map_cruxpad_themes_code['theme1_theme']) {
							notify_bottom_left(cruxpad_dialog_notification_msg.technical_error_msg, map_notify_msg_position['evernote_bottom'], parseInt(map_notify_msg_position['evernote_left']) + parseInt(dialog_left_position) - parseInt(map_notify_msg_position['evernote_theme1_decrement_value']));
						}

					},
					success : function(data1) {
						url_msg = data1.message;
						$("#cruxpad_notify_msg").hide();
						if (dialog_display_theme === map_cruxpad_themes_code['default_theme']) {
							notify_bottom_left(url_msg, map_notify_msg_position['evernote_bottom'], map_notify_msg_position['evernote_left']);
						} else if (dialog_display_theme === map_cruxpad_themes_code['theme1_theme']) {
							notify_bottom_left(url_msg, map_notify_msg_position['evernote_bottom'], parseInt(map_notify_msg_position['evernote_left']) + parseInt(dialog_left_position) - parseInt(map_notify_msg_position['evernote_theme1_decrement_value']));
						}
						if (data1.successFlag == "A") {
							save_evernote_cnt++;
							open_evernote_oauth_window();
						}
					}
				});

			} else if (data.statusFlag == "N") {
				save_evernote_cnt++;
				if (dialog_display_theme === map_cruxpad_themes_code['default_theme']) {
					notify_bottom_left(cruxpad_dialog_notification_msg.evernote_connect_msg, map_notify_msg_position['evernote_bottom'], map_notify_msg_position['evernote_left']);
				} else if (dialog_display_theme === map_cruxpad_themes_code['theme1_theme']) {
					notify_bottom_left(cruxpad_dialog_notification_msg.evernote_connect_msg, map_notify_msg_position['evernote_bottom'], parseInt(map_notify_msg_position['evernote_left']) + parseInt(dialog_left_position) - parseInt(map_notify_msg_position['evernote_theme1_decrement_value']));
				}
				open_evernote_oauth_window();
			} else {
				if (dialog_display_theme === map_cruxpad_themes_code['default_theme']) {
					notify_bottom_left(url_msg, map_notify_msg_position['evernote_bottom'], map_notify_msg_position['evernote_left']);
				} else if (dialog_display_theme === map_cruxpad_themes_code['theme1_theme']) {
					notify_bottom_left(url_msg, map_notify_msg_position['evernote_bottom'], parseInt(map_notify_msg_position['evernote_left']) + parseInt(dialog_left_position) - parseInt(map_notify_msg_position['evernote_theme1_decrement_value']));
				}
			}
		}
	});

}

function open_evernote_oauth_window() {
	var link = map_cruxpad_urls['evernote_wait_url'] + '?pluginId=' + plugin_id;
	window.open(link, '_blank', 'menubar=yes,toolbar=yes,resizable=yes,scrollbars=yes,height=600,width=700');
	window.focus();
}

function show_timesave_text(show_flag) {
	if (show_flag && time_saved_in_minutes > 0.1) {
		$('#time_saved').show();
	} else {
		$('#time_saved').hide();
	}
}

function show_recaptcha(element) {
	Recaptcha.create(recaptcha_public_key, element, {
		theme : "red",
		callback : Recaptcha.focus_response_field
	});
}

function handle_captcha() {

}

/********************************** ALL SHARING AND SAVING FUNCTIONS ENDS *******************/

/********************************** ALL ANALYTICS FUNCTIONS STARTS ******************/

get_analytics_from_local_storage();

//log_console_msg("Analytics :-" + (localStorage['analytics'] != null ? JSON.stringify(localStorage['analytics']) : ''));

/**
 * This function is called onbeforeunload and onclose It prepares analytics json
 * and stores it in chrome storage.
 *
 * @author Yash
 */

function update_analytics() {

	// if (json_response && json_response.summaryFlag && json_response.summaryFlag == 'Y') {
	if (!document.getElementById('view_summary_flag') && json_response.summaryFlag == 'Y') {

		var analytics_json = {
			"pluginId" : plugin_id, // 1
			"reqUrl" : encodeURIComponent(url), // 2
			"mainFrameActiveTotalCnt" : crux_dialog_active_cnt, // 3
			"mainFrameActiveTotalTime" : crux_dialog_active_time, // 4
			"mainfrmActiveSrcFooterCnt" : crux_footer_to_dialog_open_cnt, // 5
			"mainfrmActiveSrcRightclickCnt" : crux_rightclick_to_dialog_open_cnt, // 6
			"mainfrmActiveSrcButtonCnt" : crux_button_to_dialog_cnt, // 7
			"footerActiveTotalTime" : crux_footer_active_time, // 8
			"facebookSharedCnt" : facebook_shared_cnt, // 9
			"twitterSharedCnt" : twitter_shared_cnt, // 10
			"googleplusSharedCnt" : googleplus_shared_cnt, // 11
			"facebookLikeCnt" : facebook_like_cnt, // 12
			"googleplusLikeCnt" : googleplus_like_cnt, // 13
			"isReportedBadSummary" : is_reported_bad_summary, // 14
			"screenX" : screen.width, // 15
			"screenY" : screen.height, // 16
			"timeSaved" : time_saved_in_minutes, // 17
			"selectAllCnt" : select_all_cnt, // 18
			"savePocketCnt" : save_pocket_cnt, // 19
			"saveEvernoteCnt" : save_evernote_cnt, // 20
			"footerActiveTotalCnt" : footer_active_total_cnt, // 21
			"mainfrmActiveSrcFocusClick" : crux_focuswords_to_dialog_open_cnt, //22
			"mainfrmActiveSrcSearchInput" : crux_searchword_to_dialog_open_cnt, //23
			"footerSearchInputUseCnt" : footer_searchword_input_used_cnt, //24
			"footerColorpickerColorChngCnt" : footer_colorpicker_color_change_cnt, //25
			"footerSliderChngCnt" : footer_slider_change_cnt, //26
			"printSummaryCnt" : print_summary_cnt, //27
			"footerFacebookSharedCnt" : footer_facebook_share_cnt, // 28
			"footerTwitterSharedCnt" : footer_twitter_share_cnt, // 29
			"feedbackClickCnt" : feedback_click_cnt, //30
			"feedbackSendCnt" : feedback_send_cnt //31
		};
		if (localStorage['analytics']) {
			cruxpad_analytics = JSON.parse(localStorage['analytics']);
		}
		cruxpad_analytics.analytics.push(analytics_json);
		localStorage['analytics'] = JSON.stringify(cruxpad_analytics);
	}

	// }
}

function get_analytics_from_local_storage() {

	if (localStorage['analytics'] != null) {
		cruxpad_analytics = JSON.parse(localStorage['analytics']);
		log_console_msg("get_analytics_from_local_storage() :-" + JSON.stringify(cruxpad_analytics));
		log_console_msg("Analytics Length: " + cruxpad_analytics.analytics.length);

		if (cruxpad_analytics != null && cruxpad_analytics.analytics.length >= 5) {
			// send then and clear storage
			$.ajax({
				url : map_cruxpad_urls['analytics_url'],
				type : "POST",
				cache : true,
				data : "analyticsJson=" + encodeURI(JSON.stringify(cruxpad_analytics.analytics)),
				error : function(jqXHR, textStatus, errorThrown) {
					log_console_msg("analytics error :-" + errorThrown + " " + textStatus);
					clear_analytics_local_storage();
				},
				success : function(data) {
					log_console_msg('sent successfully');
					clear_analytics_local_storage();
				}
			});
		}
	}
}

function increment_footer_active_cnt_analytics() {
	if (disable_popup_flag == 'N') {
		footer_active_total_cnt++;
	}
}

function clear_analytics_local_storage() {
	cruxpad_analytics.analytics = [];
	localStorage['analytics'] = JSON.stringify(cruxpad_analytics);
}

/******************************** ALL ANALYTICS FUNCTIONS ENDS ******************/

/********************************* CODE FOR LISTENING EVENTS FROM CONTENT SCRIPTSTARTs******************/

function listener(event) {
	if (event.data.event_name == map_cruxpad_extensions_iframe_event_names['cruxpad_frame']) {
		if (event.data.message == map_cruxpad_extensions_iframe_received_msgs['canvas_clicked']) {
			handle_footer_dialog_transition();
			crux_button_to_dialog_cnt++;
			crux_dialog_active_cnt++;
		} else if (event.data.message == map_cruxpad_extensions_iframe_received_msgs['view_summary_clicked']) {
			handle_footer_dialog_transition();
			crux_rightclick_to_dialog_open_cnt++;
			crux_dialog_active_cnt++;
		} else if (event.data.message == map_cruxpad_extensions_iframe_received_msgs['close_footer_dialog_keyboard']) {
			reinit_slider_footer_close();
			handle_footer_dialog_close();
		} else if (event.data.message == map_cruxpad_extensions_iframe_received_msgs['show_dialog_popup_disable']) {
			handle_footer_dialog_transition();
		} else if (event.data.message == map_cruxpad_extensions_iframe_received_msgs['total_time_saved_calculated']) {
			if (event.data.total_time_saved && event.data.total_time_saved != '' && event.data.total_time_saved != '0') {
				$('#total_time_saved_text').html(" Time saved till date: " + event.data.total_time_saved + ' secs.');
			}
		}
	}

}

if (window.addEventListener) {
	window.addEventListener("message", listener, false);
} else {
	window.attachEvent("onmessage", listener);
}

/********************************** CODE FOR LISTENING EVENTS FROM CONTENT SCRIPT ENDS ******************/

/********************************** ALL WINDOW EVENT CODE STARTS *******************/

{
	/**
	 * This code is when a new page loads on the same tab.
	 *
	 * @author Yash
	 */

	// window.onbeforeunload = function() {
	// // update analytics only if the url is not blacklisted
	// update_analytics();
	// }
	/**
	 * This code is when a current tab is closed.
	 *
	 * @author Yash
	 */

	// window.onunload = function(){
	// // if(!analytics_stored_flag){
	// // analytics_stored_flag = true;

	// update_analytics();
	// // }

	// }
	// window.onclose = function(){
	// if(!analytics_stored_flag){
	// analytics_stored_flag = true;
	// update_analytics();
	// }
	// }
	window.onunload = window.onbeforeunload = ( function() {
			var analytics_stored_flag = false;
			return function() {
				if (analytics_stored_flag) {
					return;
				}
				analytics_stored_flag = true;
				update_analytics();
			}
		}());	window.onresize = function() {
		//log_console_msg("window.innerHeight :-" + window.innerHeight);
		set_dialog_window();
	}
	// Timer called for incrementing dialog and footer active time
	setInterval(function() {
		if (json_response.summaryFlag == 'Y') {
			if ($("#cruxpad_dialog").is(':visible')) {
				crux_dialog_active_time++;
			} else if ($("#cruxpad_mini_footer").is(':visible') && disable_popup_flag == 'N') {
				crux_footer_active_time++;
			}
		}
	}, 1000);

}
/** ******************************* ALL WINDOW EVENT CODE ENDS ***************** */

