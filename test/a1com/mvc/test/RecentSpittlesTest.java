package a1com.mvc.test;


import static org.mockito.Mockito.mock;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import a1com.mvc.spittr.DAO.SpittleRepository;
import a1com.mvc.spittr.constants.SpittlesViews;
import a1com.mvc.spittr.pojo.Spittle;
import a1com.mvc.spittr.web.SpittleController;

public class RecentSpittlesTest {
	// This pattern can be cut and pasted across classes.
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Test
	public void shouldShowRecentSpittles() throws Exception {
		final int sps = SpittlesViews.NUM.getNum();
		List<Spittle> spittles = createSplittes(sps);
		logger.info("{} {} spittles", LocalDateTime.now(), sps);
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(get("/spittle?max=238900&count=" + sps)).andExpect(view().name("spittles"))
//				.andExpect(model().attributeExists("spittleList"))
//				.andExpect(model().attribute("spittleList", hasItems(spittles.toArray())));
		;
	}

	private List<Spittle> createSplittes(int num) {
		List<Spittle> spittles = null;
		if (num != 0 && num >= 1) {
			spittles = new ArrayList<>(num);
			for (int i = num - 1, e = 1; i >= 0; i--, e++) {
				Spittle sps = new Spittle("message " + e);
				spittles.add(sps);
				logger.info("{} {} spittle created and added to spittleList", LocalDateTime.now(), sps.getMessage());
			}
		} else {
			spittles = new ArrayList<>(1);
			Spittle sp = new Spittle();
			sp.setMessage("1 element of spittle auto-created");
			spittles.add(sp);
			logger.info("{} {} spittle created and added to spittleList", LocalDateTime.now(), sp.getMessage());
		}
		return spittles;
	}

}
