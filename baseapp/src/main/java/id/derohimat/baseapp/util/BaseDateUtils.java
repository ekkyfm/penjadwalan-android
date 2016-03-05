package id.derohimat.baseapp.util;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import timber.log.Timber;

/**
 * Created by deni rohimat on 7/12/15.
 */
public class BaseDateUtils {

	public static SpannableStringBuilder getForumFormattedDate(long timestamp) {
		Calendar calendar = getSpesificCalendar(timestamp * DateUtils.SECOND_IN_MILLIS);

		SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
		spannableStringBuilder.append(getDay(calendar.get(Calendar.DAY_OF_WEEK))).append(", ").
				append(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))).
				append(" ").append(getMonth(calendar.get(Calendar.MONTH))).
				append(" ").append(String.valueOf(calendar.get(Calendar.YEAR)));

		spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#0187ce")), 0,
				spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		int start = spannableStringBuilder.length();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
		spannableStringBuilder.append(" ");
		spannableStringBuilder.append(simpleDateFormat.format(calendar.getTime()));

		spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#bcbcbc")), start,
				spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		return spannableStringBuilder;
	}

	private static String getDay(int day) {
		String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday", "Saturday"};
		return days[day - 1];
	}

	private static String getMonth(int month) {
		String[] months = {"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December"};
		return months[month];
	}


	public static String getCommentFormattedDate(long timestamp) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		return simpleDateFormat.format(getSpesificCalendar(timestamp).getTime());
	}

	public static String getUpdateProfileFormattedDate(long timestamp) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		return simpleDateFormat.format(getSpesificCalendar(timestamp).getTime());
	}

	public static String getHistoryFormattedDate(long timestamp) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault());
		return simpleDateFormat.format(getSpesificCalendar(timestamp).getTime());
	}

	public static String getDetailFeedFormattedDate(Context context, long timestamp) {

		timestamp = timestamp * DateUtils.SECOND_IN_MILLIS;
		Timber.v("Timestamp:" + timestamp);
		Timber.v("Now milis:" + Calendar.getInstance().getTimeInMillis());
		Timber.v("Difference:" + (timestamp - Calendar.getInstance().getTimeInMillis()));

		return getStringValue(context, ((Calendar.getInstance().getTimeInMillis()) -
				getSpesificCalendar(timestamp).getTimeInMillis()) / DateUtils.SECOND_IN_MILLIS);
	}

	private static Calendar getSpesificCalendar(long timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp);
		return calendar;
	}

	private static String getStringValue(Context context, long difference) {

		Timber.v("second diff:" + difference);

		String time;
		if (difference > (365 * 60 * 60 * 24)) {
			time = difference / (365 * 60 * 60 * 24) + " tahun yang lalu";
		} else if (difference > (60 * 60 * 24)) {
			time = difference / (60 * 60 * 24) + " hari yang lalu";
		} else if (difference > (60 * 60)) {
			time = difference / (60 * 60) + " jam yang lalu";
		} else if (difference > 60) {
			time = difference / (60) + " menit yang lalu";
		} else {
			time = "beberapa detik yang lalu";
		}

		int count;
		try {
			count = Integer.parseInt(time.substring(0, 2).trim());
			if (count == 1) {
				if (!time.contains("detik")) {
					time = time.replace("", "");
				}
			}
		} catch (Exception ignored) {
		}

		return time;
	}


}
