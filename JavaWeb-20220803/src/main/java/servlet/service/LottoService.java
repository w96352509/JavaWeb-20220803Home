package servlet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoService {
	public List<Integer> getLottos(int count) {
		Random random = new Random();
		List<Integer> lottos = new ArrayList<>();
		for(int i=0;i<count;i++) {
			lottos.add(random.nextInt(100)); // 0~99
		}
		return lottos;
	}
}
