package filters;

import ninja.*;
import ninja.session.Session;

/**
 * Created by Trent on 5/7/2017.
 */
public class AuthFilter implements Filter {
    @Override
    public Result filter(FilterChain filterChain, Context context) {
        Session session = context.getSession();
        if (session == null || session.get("username") == null)
        {
            context.getFlashScope().error("Please log in.");
            return Results.redirect("/admin/login");
        }
        return filterChain.next(context);
    }
}
