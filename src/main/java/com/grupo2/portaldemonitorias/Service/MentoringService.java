import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.portaldemonitorias.DTO.MentoringDTO;
import com.grupo2.portaldemonitorias.Model.Mentoring;
import com.grupo2.portaldemonitorias.Repository.MentoringRepository;
import com.grupo2.portaldemonitorias.Repository.ProfessorRepository;
import com.grupo2.portaldemonitorias.Repository.StudentRepository;

@Service
public class MentoringService {

    public MentoringService() {}

    @Transactional
    public Mentoring save (MentoringDTO mentoringDTO) {

        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private ProfessorRepository professorRepository;

        @Autowired
        private MentoringRepository mentoringRepository;

        //service estudante
        private StudentService studentService = new StudentService();

        //service professor
        private ProfessorService professorService = new ProfessorService();
        
        
        Mentoring mentoring= MentoringMapper.MAPPER.mentoringDTO(mentoringDTO);

        MentoringRepository.save(mentoring);
    }

    @Transactional
    public Mentoring delete (@PathVariable Long id) {
        Optional<Mentoring> mentoringGetter = MentoringRepository.findByidMentoring(id);
        Mentoring mentoring = mentoringGetter.get();

        MentoringService.delete(mentoring.getDescription());
        MentoringService.delete(mentoring.getDiscipline());

        //Tem que ligar com professor
        //Tem que ligar com estudante

        mentoringRepository.delete(mentoring);

    }

    //Metodos extras

}
