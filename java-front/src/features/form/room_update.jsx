import {useMemo} from 'react';
import {useState} from 'react'
import {updateData} from '../../actions/actions';
import Input from '../../components/input/input'
import './form.css'
import CheckBox from "../../components/input/checkbox";

export default function UpdateRoomForm({idf, close}) {
    const [id,] = useState(idf['id']);
    const [type, setType] = useState(idf['type']);
    const [style, setStyle] = useState(idf['style']);
    const [level, setLevel] = useState(idf['level']);

    const memoDisabled = useMemo(() => {
        return (!id);
    }, [id, type, style, level])

    const submit = (e) => {
        e.preventDefault();
        updateData('room', {
            id: id,
            type: type,
            style: style,
            level: level
        }, id)
    }

    return (
        <form className="container_form" onSubmit={submit}>
            <div className="button_form">
                <button type="button" onClick={close}>Закрыть</button>
                <button type="submit" className={memoDisabled ? 'disabled' : ''} disabled={memoDisabled}>Отправить
                </button>
            </div>
            <Input disabled={true} label='id' value={id}/>
            <Input value={type} label='Тип' change={(e) => setType(e.value)}/>
            <Input value={style} label='Стиль' change={(e) => setStyle(e.value)}/>
            <Input value={level} label='Этаж' change={(e) => setLevel(e.value)}/>
        </form>
    )
}